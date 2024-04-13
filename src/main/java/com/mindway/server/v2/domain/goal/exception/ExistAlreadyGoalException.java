package com.mindway.server.v2.domain.goal.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class ExistAlreadyGoalException extends MindWayException {
    public ExistAlreadyGoalException() {super(ErrorCode.EXIST_ALREADY_GOAL);}
}
