package com.mindway.server.v2.domain.event.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class InvalidStartAndEndDateException extends MindWayException {
    public InvalidStartAndEndDateException() {
        super(ErrorCode.INVALID_START_AND_END_DATE);
    }
}
