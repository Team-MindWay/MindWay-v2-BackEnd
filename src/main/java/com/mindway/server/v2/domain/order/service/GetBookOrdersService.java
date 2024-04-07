package com.mindway.server.v2.domain.order.service;

import com.mindway.server.v2.domain.order.presentation.dto.response.OrdersResponse;

import java.util.List;

public interface GetBookOrdersService {
    List<OrdersResponse> execute();
}
