package com.mindway.server.v2.domain.order.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class NotFoundOrderException extends MindWayException {
    public NotFoundOrderException() { super(ErrorCode.ORDER_NOT_FOUND);}
}
