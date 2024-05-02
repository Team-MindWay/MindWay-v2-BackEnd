package com.mindway.server.v2.domain.order.presentation;

import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderUpdateRequest;
import com.mindway.server.v2.domain.order.presentation.dto.response.OrdersResponse;
import com.mindway.server.v2.domain.order.service.BookRequestService;
import com.mindway.server.v2.domain.order.service.DeleteBookOrderService;
import com.mindway.server.v2.domain.order.service.GetBookOrdersService;
import com.mindway.server.v2.domain.order.service.UpdateBookOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/order")
public class OrdersController {
    private final BookRequestService bookRequestService;
    private final DeleteBookOrderService deleteBookOrderService;
    private final UpdateBookOrderService updateBookOrderService;
    private final GetBookOrdersService getBookOrdersService;

    @PostMapping
    public ResponseEntity<Void> bookRequest (@RequestBody @Valid OrderRequest bookRequest) {
        bookRequestService.execute(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteBook (@PathVariable("order_id") Long id) {
        deleteBookOrderService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{order_id}")
    public ResponseEntity<Void> updateBook (
        @PathVariable("order_id") Long id,
        @RequestBody @Valid OrderUpdateRequest orderUpdate
    ) {
        updateBookOrderService.execute(id, orderUpdate);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> getBookOrders () {
        List<OrdersResponse> orders = getBookOrdersService.execute();
        return ResponseEntity.ok(orders);
    }

}
