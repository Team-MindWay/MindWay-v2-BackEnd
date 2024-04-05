package com.mindway.server.v2.domain.order.util.impl;

import com.mindway.server.v2.domain.order.entity.BookType;
import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.util.OrdersConverter;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrdersConverterImpl implements OrdersConverter {
    @Override
    public Orders toEntity(OrderRequest bookRequest, User user, BookType bookType) {
        return Orders.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .bookURL(bookRequest.getBook_url())
                .bookType(bookType)
                .user(user)
                .build();
    }
}