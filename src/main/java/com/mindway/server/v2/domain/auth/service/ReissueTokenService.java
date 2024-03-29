package com.mindway.server.v2.domain.auth.service;

import com.mindway.server.v2.domain.auth.presentation.dto.response.TokenResponse;

public interface ReissueTokenService {
    TokenResponse execute(String refreshToken);
}
