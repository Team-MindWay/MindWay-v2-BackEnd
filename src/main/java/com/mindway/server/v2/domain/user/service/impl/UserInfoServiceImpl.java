package com.mindway.server.v2.domain.user.service.impl;

import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.presentation.dto.response.UserInfoResponse;
import com.mindway.server.v2.domain.user.service.UserInfoService;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithReadOnlyTransaction
public class UserInfoServiceImpl implements UserInfoService {

    private final UserUtil userUtil;

    public UserInfoResponse execute() {
        User user = userUtil.getCurrentUser();
        return new UserInfoResponse(user.getName(), user.getAuthority());
    }
}
