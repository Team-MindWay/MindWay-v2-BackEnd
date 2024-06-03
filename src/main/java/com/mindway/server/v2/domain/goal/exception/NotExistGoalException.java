package com.mindway.server.v2.domain.goal.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class NotExistGoalException extends MindWayException {
    public NotExistGoalException() {
        super(ErrorCode.NOT_EXIST_GOAL);
    }
}
