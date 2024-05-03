package com.mindway.server.v2.domain.auth.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class InvalidTokenException extends MindWayException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
