package com.mindway.server.v2.domain.book.service.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.exception.BookNotFoundException;
import com.mindway.server.v2.domain.book.exception.NotSameAuthorException;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookUpdateRequest;
import com.mindway.server.v2.domain.book.repository.BookRepository;
import com.mindway.server.v2.domain.book.service.BookUpdateService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class BookUpdateServiceImpl implements BookUpdateService {

    private final UserUtil userUtil;
    private final BookRepository bookRepository;

    public void execute(BookUpdateRequest bookUpdateRequest, Long id) {
        User user = userUtil.getCurrentUser();
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        if (user != book.getUser()){
            throw new NotSameAuthorException();
        }

        book.updateBook(bookUpdateRequest);

        bookRepository.save(book);
    }
}
