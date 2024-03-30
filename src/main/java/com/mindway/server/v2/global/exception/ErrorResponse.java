package com.mindway.server.v2.global.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
public record ErrorResponse(int status, String message) {
}