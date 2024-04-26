package com.mindway.server.v2.domain.event.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class InvalidUserEventException extends MindWayException {
    public InvalidUserEventException() {
        super(ErrorCode.INVALID_USER_EVENT);
    }
}
