package com.mindway.server.v2.domain.auth.service.impl;

import com.mindway.server.v2.domain.auth.RefreshToken;
import com.mindway.server.v2.domain.auth.exception.ExpiredRefreshTokenException;
import com.mindway.server.v2.domain.auth.exception.UserNotFoundException;
import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;
import com.mindway.server.v2.domain.auth.repository.RefreshRepository;
import com.mindway.server.v2.domain.auth.service.ReissueTokenService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.repository.UserRepository;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import com.mindway.server.v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransaction
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtProvider jwtProvider;
    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository;

    public TokenResponse execute(String refreshToken) {
        String parseRefreshToken = jwtProvider.parseRefreshToken(refreshToken);

        RefreshToken refreshEntity = refreshRepository.findById(parseRefreshToken)
                .orElseThrow(ExpiredRefreshTokenException::new);

        User user = userRepository.findById(refreshEntity.getMemberId())
                .orElseThrow(UserNotFoundException::new);

        refreshRepository.deleteById(refreshEntity.getRefreshToken());

        return jwtProvider.generateTokenDto(user.getId());
    }
}
