package com.mindway.server.v2.domain.rec.execption;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class NotFoundRecommendException extends MindWayException {
    public NotFoundRecommendException () {
        super(ErrorCode.NOT_FOUND_RECOMMEND_BOOK);
    }
}
