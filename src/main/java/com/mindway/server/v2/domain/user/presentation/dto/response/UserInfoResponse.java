package com.mindway.server.v2.domain.user.presentation.dto.response;

import com.mindway.server.v2.domain.user.entity.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponse {
    private String name;
    private Authority authority;

    public UserInfoResponse(String name, Authority authority) {
        this.name = name;
        this.authority = authority;
    }
}
