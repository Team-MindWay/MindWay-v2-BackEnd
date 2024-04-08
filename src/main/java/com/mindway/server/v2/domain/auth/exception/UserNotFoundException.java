package com.mindway.server.v2.domain.auth.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class UserNotFoundException extends MindWayException {

    public UserNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
