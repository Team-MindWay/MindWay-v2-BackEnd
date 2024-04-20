package com.mindway.server.v2.domain.book.util.impl;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookInfoResponse;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookListResponse;
import com.mindway.server.v2.domain.book.util.BookConverter;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class BookConverterImpl implements BookConverter {

    public Book toEntity(BookWriteRequest bookWriteRequest, User user) {
        return Book.builder()
                .title(bookWriteRequest.getTitle())
                .plot(bookWriteRequest.getPlot())
                .user(user)
                .build();
    }

    public BookInfoResponse toDto(Book book) {
        return BookInfoResponse.builder()
                .title(book.getTitle())
                .plot(book.getPlot())
                .build();
    }

    public BookListResponse toListDto(Book book) {
        return BookListResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .plot(book.getPlot())
                .date(book.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
