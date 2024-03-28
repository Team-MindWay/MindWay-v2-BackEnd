package com.mindway.server.v2.domain.auth.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class MemberNotFoundException extends MindWayException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
