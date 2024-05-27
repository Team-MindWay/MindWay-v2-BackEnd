package com.mindway.server.v2.domain.order.util.impl;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.presentation.dto.response.OrdersResponse;
import com.mindway.server.v2.domain.order.util.OrdersConverter;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrdersConverterImpl implements OrdersConverter {
    @Override
    public Orders toEntity(OrderRequest bookRequest, User user) {
        return Orders.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .bookURL(bookRequest.getBook_url())
                .user(user)
                .build();
    }

    @Override
    public OrdersResponse toDto(Orders orders) {
        return OrdersResponse.builder()
                .id(orders.getId())
                .title(orders.getTitle())
                .author(orders.getAuthor())
                .book_url(orders.getBookURL())
                .build();
    }
}
