package com.mindway.server.v2.domain.book.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class BookNotFoundException extends MindWayException {
    public BookNotFoundException() {
        super(ErrorCode.NOT_FOUND_BOOK);
    }
}
