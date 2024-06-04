package com.mindway.server.v2.domain.book.service.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.exception.NotSameAuthorException;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookListResponse;
import com.mindway.server.v2.domain.book.repository.BookRepository;
import com.mindway.server.v2.domain.book.service.GetBookListService;
import com.mindway.server.v2.domain.book.util.BookConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class GetBookListServiceImpl implements GetBookListService {
    private final UserUtil userUtil;
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public List<BookListResponse> execute() {
        User user = userUtil.getCurrentUser();
        List<Book> books = bookRepository.findAllByUser(user);

        return books.stream()
                .map(bookConverter::toListDto)
                .collect(Collectors.toList());
    }
}
