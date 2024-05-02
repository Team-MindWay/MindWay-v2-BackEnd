package com.mindway.server.v2.domain.book.presentation;

import com.mindway.server.v2.domain.book.presentation.dto.request.BookUpdateRequest;
import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookInfoResponse;
import com.mindway.server.v2.domain.book.presentation.dto.response.BookListResponse;
import com.mindway.server.v2.domain.book.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/book")
public class BookController {

    private final BookWriteService bookWriteService;
    private final BookDeleteService bookDeleteService;
    private final BookUpdateService bookUpdateService;
    private final GetBookService getBookService;
    private final GetBookListService getBookListService;

    @PostMapping
    public ResponseEntity<Void> writeBookReport (@RequestBody @Valid BookWriteRequest bookWriteRequest) {
        bookWriteService.execute(bookWriteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> deleteBookReport (@PathVariable("book_id") Long id) {
        bookDeleteService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{book_id}")
    public ResponseEntity<Void> updateBookReport (
        @PathVariable("book_id") Long id,
        @RequestBody @Valid BookUpdateRequest bookUpdateRequest
    ) {
        bookUpdateService.execute(bookUpdateRequest, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookInfoResponse> getDetailBookReport (@PathVariable("book_id") Long id) {
        BookInfoResponse bookInfoResponse = getBookService.execute(id);
        return ResponseEntity.ok(bookInfoResponse);
    }

    @GetMapping
    public ResponseEntity<List<BookListResponse>> getBookReportList () {
        List<BookListResponse> bookList = getBookListService.execute();
        return ResponseEntity.ok(bookList);
    }
}
