package com.mindway.server.v2.domain.order.presentation;

import com.mindway.server.v2.domain.order.entity.BookType;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.service.BookRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/order")
public class OrdersController {
    private final BookRequestService bookRequestService;

    @PostMapping()
    public ResponseEntity<Void> bookRequest
            (@RequestParam BookType type, @RequestBody @Valid OrderRequest bookRequest) {
        bookRequestService.execute(type, bookRequest);
        return ResponseEntity.ok().build();
    }

}
