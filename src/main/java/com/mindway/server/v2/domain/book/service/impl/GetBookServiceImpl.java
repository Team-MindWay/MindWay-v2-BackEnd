package com.mindway.server.v2.domain.book.service.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.exception.BookNotFoundException;
import com.mindway.server.v2.domain.book.exception.NotSameAuthorException;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookInfoResponse;
import com.mindway.server.v2.domain.book.repository.BookRepository;
import com.mindway.server.v2.domain.book.service.GetBookService;
import com.mindway.server.v2.domain.book.util.BookConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class GetBookServiceImpl implements GetBookService {

    private final UserUtil userUtil;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public BookInfoResponse execute(Long id) {
        User user = userUtil.getCurrentUser();
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        if (user != book.getUser()) {
            throw new NotSameAuthorException();
        }

        return bookConverter.toDto(book);
    }
}
