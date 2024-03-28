package com.mindway.server.v2.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpiresIn;
    private LocalDateTime refreshTokenExpiresIn;
}
