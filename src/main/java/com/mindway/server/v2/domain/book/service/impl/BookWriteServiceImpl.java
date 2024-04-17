package com.mindway.server.v2.domain.book.service.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.repository.BookRepository;
import com.mindway.server.v2.domain.book.service.BookWriteService;
import com.mindway.server.v2.domain.book.util.BookConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class BookWriteServiceImpl implements BookWriteService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final UserUtil userUtil;

    public void execute(BookWriteRequest bookWriteRequest) {
        User user = userUtil.getCurrentUser();

        Book book = bookConverter.toEntity(bookWriteRequest, user);
        bookRepository.save(book);
    }
}
