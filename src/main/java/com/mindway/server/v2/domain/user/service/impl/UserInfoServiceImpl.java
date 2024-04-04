package com.mindway.server.v2.domain.user.service.impl;

import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.presentation.dto.response.UserInfoResponse;
import com.mindway.server.v2.domain.user.service.UserInfoService;
import com.mindway.server.v2.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class UserInfoServiceImpl implements UserInfoService {

    private final UserUtil userUtil;

    public UserInfoResponse execute() {
        User user = userUtil.getCurrentUser();
        return new UserInfoResponse(user.getName(), user.getAuthority());
    }
}
