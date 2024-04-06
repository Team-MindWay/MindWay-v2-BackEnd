package com.mindway.server.v2.domain.order.presentation;

import com.mindway.server.v2.domain.order.entity.BookType;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.service.BookRequestService;
import com.mindway.server.v2.domain.order.service.DeleteBookOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/order")
public class OrdersController {
    private final BookRequestService bookRequestService;
    private final DeleteBookOrderService deleteBookOrderService;

    @PostMapping()
    public ResponseEntity<Void> bookRequest
            (@RequestParam BookType type, @RequestBody @Valid OrderRequest bookRequest) {
        bookRequestService.execute(type, bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long order_id) {
        deleteBookOrderService.execute(order_id);
        return ResponseEntity.noContent().build();
    }

}
