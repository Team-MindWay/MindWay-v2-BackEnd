package com.mindway.server.v2.domain.book.service;

import com.mindway.server.v2.domain.book.presentation.dto.response.BookInfoResponse;

public interface GetBookService {
    BookInfoResponse execute(Long id);
}
