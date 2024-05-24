package com.mindway.server.v2.global.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(int status, String message) {}