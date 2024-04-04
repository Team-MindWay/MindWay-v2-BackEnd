package com.mindway.server.v2.domain.user.presentation.dto.response;

import com.mindway.server.v2.domain.user.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private String name;
    private Authority authority;
}
