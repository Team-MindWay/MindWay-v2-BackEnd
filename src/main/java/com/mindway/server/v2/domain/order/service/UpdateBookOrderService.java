package com.mindway.server.v2.domain.order.service;

import com.mindway.server.v2.domain.order.presentation.dto.request.OrderUpdate;

public interface UpdateBookOrderService {
    void execute(Long id, OrderUpdate orderUpdate);
}
