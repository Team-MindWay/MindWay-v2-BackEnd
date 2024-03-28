package com.mindway.server.v2.domain.auth.service.impl;

import com.mindway.server.v2.domain.auth.RefreshToken;
import com.mindway.server.v2.domain.auth.presentation.dto.request.SignInRequest;
import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;
import com.mindway.server.v2.domain.auth.repository.RefreshRepository;
import com.mindway.server.v2.domain.auth.service.SignInService;
import com.mindway.server.v2.domain.member.entity.Member;
import com.mindway.server.v2.domain.member.entity.StudentNum;
import com.mindway.server.v2.domain.member.repository.MemberRepository;
import com.mindway.server.v2.global.security.jwt.JwtProvider;
import gauth.GAuth;
import gauth.GAuthToken;
import gauth.GAuthUserInfo;
import gauth.exception.GAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final GAuth gAuth;
    private final RefreshRepository refreshRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Value("gauth.clientId")
    private String clientId;
    @Value("gauth.clientSecret")
    private String clientSecret;
    @Value("gauth.redirectUri")
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

            Member member = memberRepository.findByEmail(userInfo.getEmail())
                    .orElse(saveMember(userInfo));

            TokenResponse tokenResponse = jwtProvider.generateTokenDto(member.getId());

            saveRefreshToken(tokenResponse, member);

            return tokenResponse;

        } catch (GAuthException e) {
            throw new GAuthException(e.getCode());
        } catch (IOException e) {
        }

        return null;
    }

    private Member saveMember(GAuthUserInfo gAuthUserInfo) {
        Member member = Member.builder()
                .email(gAuthUserInfo.getEmail())
                .name(gAuthUserInfo.getName())
                .studentNum(new StudentNum(gAuthUserInfo.getGrade(), gAuthUserInfo.getClassNum(), gAuthUserInfo.getNum()))
                .build();

        memberRepository.save(member);

        return member;
    }

    private void saveRefreshToken(TokenResponse tokenResponse, Member member) {
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(tokenResponse.getRefreshToken())
                .memberId(member.getId())
                .expiredAt(tokenResponse.getRefreshTokenExpiresIn())
                .build();

        refreshRepository.save(refreshToken);
    }
}

