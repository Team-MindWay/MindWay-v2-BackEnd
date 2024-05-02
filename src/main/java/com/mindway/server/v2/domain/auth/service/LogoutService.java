package com.mindway.server.v2.domain.auth.service;

public interface LogoutService {
    void execute(String accessToken);
}
