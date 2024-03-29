package com.mindway.server.v2.domain.auth.service.impl;

import com.mindway.server.v2.domain.auth.RefreshToken;
import com.mindway.server.v2.domain.auth.exception.ExpiredRefreshTokenException;
import com.mindway.server.v2.domain.auth.repository.RefreshRepository;
import com.mindway.server.v2.domain.auth.service.LogoutService;
import com.mindway.server.v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final RefreshRepository refreshRepository;
    private final JwtProvider jwtProvider;

    public void execute(String refreshToken) {
        String parseRefreshToken = jwtProvider.parseRefreshToken(refreshToken);

        RefreshToken validRefreshToken = refreshRepository.findById(parseRefreshToken)
                .orElseThrow(ExpiredRefreshTokenException::new);

        refreshRepository.deleteById(validRefreshToken.getRefreshToken());

    }

}
