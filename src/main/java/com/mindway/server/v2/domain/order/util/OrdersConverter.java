package com.mindway.server.v2.domain.order.util;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.presentation.dto.response.OrdersResponse;
import com.mindway.server.v2.domain.user.entity.User;

public interface OrdersConverter {
    Orders toEntity (OrderRequest bookRequest, User user);

    OrdersResponse toDto (Orders orders);
}
