package com.mindway.server.v2.domain.auth.service.impl;

import com.mindway.server.v2.domain.auth.RefreshToken;
import com.mindway.server.v2.domain.auth.exception.ExpiredRefreshTokenException;
import com.mindway.server.v2.domain.auth.exception.MemberNotFoundException;
import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;
import com.mindway.server.v2.domain.auth.repository.RefreshRepository;
import com.mindway.server.v2.domain.auth.service.ReissueTokenService;
import com.mindway.server.v2.domain.member.entity.Member;
import com.mindway.server.v2.domain.member.repository.MemberRepository;
import com.mindway.server.v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtProvider jwtProvider;
    private final RefreshRepository refreshRepository;
    private final MemberRepository memberRepository;

    public TokenResponse execute(String refreshToken) {
        String parseRefreshToken = jwtProvider.parseRefreshToken(refreshToken);

        RefreshToken refreshEntity = refreshRepository.findById(parseRefreshToken)
                .orElseThrow(ExpiredRefreshTokenException::new);

        Member member = memberRepository.findById(refreshEntity.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        refreshRepository.deleteById(refreshEntity.getRefreshToken());

        return jwtProvider.generateTokenDto(member.getId());
    }
}
