package com.mindway.server.v2.domain.book.service;

import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;

public interface BookWriteService {
    void execute(BookWriteRequest bookWriteRequest);
}
