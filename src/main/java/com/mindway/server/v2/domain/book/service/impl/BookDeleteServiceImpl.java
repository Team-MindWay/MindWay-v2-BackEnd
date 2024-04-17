package com.mindway.server.v2.domain.book.service.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.exception.BookNotFoundException;
import com.mindway.server.v2.domain.book.exception.NotSameAuthorException;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.repository.BookRepository;
import com.mindway.server.v2.domain.book.service.BookDeleteService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class BookDeleteServiceImpl implements BookDeleteService {

    private final BookRepository bookRepository;
    private final UserUtil userUtil;

    public void execute(Long id) {
        User user = userUtil.getCurrentUser();
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        if (user != book.getUser()) {
            throw new NotSameAuthorException();
        }

        bookRepository.delete(book);
    }
}
