package com.mindway.server.v2.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MindWayException extends RuntimeException{
    private final ErrorCode errorCode;
}