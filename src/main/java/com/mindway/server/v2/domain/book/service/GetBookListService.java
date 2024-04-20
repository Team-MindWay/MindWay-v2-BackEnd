package com.mindway.server.v2.domain.book.service;

import com.mindway.server.v2.domain.book.presentation.dto.response.BookListResponse;

import java.util.List;

public interface GetBookListService {
    List<BookListResponse> execute();
}
