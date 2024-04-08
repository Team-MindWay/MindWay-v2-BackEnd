package com.mindway.server.v2.domain.auth.service.impl;

import com.mindway.server.v2.domain.auth.RefreshToken;
import com.mindway.server.v2.domain.auth.presentation.dto.request.SignInRequest;
import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;
import com.mindway.server.v2.domain.auth.repository.RefreshRepository;
import com.mindway.server.v2.domain.auth.service.SignInService;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.entity.StudentNum;
import com.mindway.server.v2.domain.user.repository.UserRepository;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import com.mindway.server.v2.global.security.jwt.JwtProvider;
import gauth.GAuth;
import gauth.GAuthToken;
import gauth.GAuthUserInfo;
import gauth.exception.GAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@ServiceWithTransaction
@Slf4j
public class SignInServiceImpl implements SignInService {

    private final GAuth gAuth;
    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Value("${gauth.clientId}")
    private String clientId;
    @Value("${gauth.clientSecret}")
    private String clientSecret;
    @Value("${gauth.redirectUri}")
    private String redirectUri;

    public TokenResponse execute(SignInRequest signInRequest) throws GAuthException{
        try {
            GAuthToken gAuthToken = gAuth.generateToken(
                    signInRequest.getCode(),
                    clientId,
                    clientSecret,
                    redirectUri
            );
            GAuthUserInfo userInfo = gAuth.getUserInfo(gAuthToken.getAccessToken());

            User user = userRepository.findByEmail(userInfo.getEmail())
                    .orElseGet(() -> saveUser(userInfo));

            TokenResponse tokenResponse = jwtProvider.generateTokenDto(user.getId());

            saveRefreshToken(tokenResponse, user);

            return tokenResponse;

        } catch (GAuthException e) {
            throw new GAuthException(e.getCode());
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return null;
    }

    private User saveUser(GAuthUserInfo gAuthUserInfo) {
        if (Objects.equals(gAuthUserInfo.getRole(), "ROLE_STUDENT")) {
            return saveStudent(gAuthUserInfo);
        } else if (Objects.equals(gAuthUserInfo.getRole(), "ROLE_TEACHER")) {
            return saveTeacher(gAuthUserInfo);
        }
        return null;
    }

    private User saveStudent(GAuthUserInfo gAuthUserInfo) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .email(gAuthUserInfo.getEmail())
                .name(gAuthUserInfo.getName())
                .studentNum(new StudentNum(gAuthUserInfo.getGrade(), gAuthUserInfo.getClassNum(), gAuthUserInfo.getNum()))
                .gauth_role(gAuthUserInfo.getRole())
                .authority(Authority.ROLE_STUDENT)
                .build();

        userRepository.save(user);

        return user;
    }

    private User saveTeacher(GAuthUserInfo gAuthUserInfo) {
        User teacher = User.builder()
                .id(UUID.randomUUID())
                .email(gAuthUserInfo.getEmail())
                .name(gAuthUserInfo.getName())
                .studentNum(new StudentNum(gAuthUserInfo.getGrade(), gAuthUserInfo.getClassNum(), gAuthUserInfo.getNum()))
                .authority(Authority.ROLE_TEACHER)
                .build();

        userRepository.save(teacher);

        return teacher;
    }

    private void saveRefreshToken(TokenResponse tokenResponse, User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(tokenResponse.getRefreshToken())
                .memberId(user.getId())
                .expiredAt(tokenResponse.getRefreshTokenExpiresIn())
                .build();

        refreshRepository.save(refreshToken);
    }
}

