package com.mindway.server.v2.domain.event.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class NotFoundEventException extends MindWayException {
    public NotFoundEventException() {
        super(ErrorCode.NOT_FOUND_EVENT);
    }
}
