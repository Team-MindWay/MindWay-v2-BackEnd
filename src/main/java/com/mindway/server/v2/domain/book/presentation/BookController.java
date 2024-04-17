package com.mindway.server.v2.domain.book.presentation;

import com.mindway.server.v2.domain.book.presentation.dto.request.BookWriteRequest;
import com.mindway.server.v2.domain.book.service.BookWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/book")
public class BookController {

    private final BookWriteService bookWriteService;

    @PostMapping
    public ResponseEntity<Void> writeBookReport (@RequestBody @Valid BookWriteRequest bookWriteRequest) {
        bookWriteService.execute(bookWriteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
