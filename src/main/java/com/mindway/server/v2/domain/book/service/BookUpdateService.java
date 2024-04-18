package com.mindway.server.v2.domain.book.service;

import com.mindway.server.v2.domain.book.presentation.dto.request.BookUpdateRequest;

public interface BookUpdateService {
    void execute(BookUpdateRequest bookUpdateRequest, Long id);
}
