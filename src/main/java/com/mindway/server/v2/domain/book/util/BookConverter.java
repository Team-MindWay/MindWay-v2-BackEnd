package com.mindway.server.v2.domain.book.util;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.user.entity.User;

public interface BookConverter {
    Book toEntity (BookWriteRequest bookWriteRequest, User user);
}
