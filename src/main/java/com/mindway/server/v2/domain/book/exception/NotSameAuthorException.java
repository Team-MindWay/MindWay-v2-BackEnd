package com.mindway.server.v2.domain.book.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class NotSameAuthorException extends MindWayException {
    public NotSameAuthorException() {
        super(ErrorCode.NOT_SAME_AUTHOR);
    }
}
