package com.mindway.server.v2.domain.book.util;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookInfoResponse;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookListResponse;
import com.mindway.server.v2.domain.user.entity.User;

import java.util.List;

public interface BookConverter {
    Book toEntity (BookWriteRequest bookWriteRequest, User user);

    BookInfoResponse toDto (Book book);

    BookListResponse toListDto (Book book);
}
