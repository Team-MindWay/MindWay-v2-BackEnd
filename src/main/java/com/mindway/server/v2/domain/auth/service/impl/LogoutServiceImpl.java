package com.mindway.server.v2.domain.auth.service.impl;

import com.mindway.server.v2.domain.auth.RefreshToken;
import com.mindway.server.v2.domain.auth.exception.ExpiredRefreshTokenException;
import com.mindway.server.v2.domain.auth.repository.RefreshRepository;
import com.mindway.server.v2.domain.auth.service.LogoutService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransaction
public class LogoutServiceImpl implements LogoutService {

    private final RefreshRepository refreshRepository;
    private final UserUtil userUtil;

    public void execute() {
        User user = userUtil.getCurrentUser();

        RefreshToken validRefreshToken = refreshRepository.findByMemberId(user.getId())
                .orElseThrow(ExpiredRefreshTokenException::new);

        refreshRepository.deleteById(validRefreshToken.getRefreshToken());
    }

}
