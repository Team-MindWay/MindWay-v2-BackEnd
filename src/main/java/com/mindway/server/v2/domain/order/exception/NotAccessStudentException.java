package com.mindway.server.v2.domain.order.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class NotAccessStudentException extends MindWayException {
    public NotAccessStudentException() {super(ErrorCode.NOT_ACCESS_STUDENT);}
}
