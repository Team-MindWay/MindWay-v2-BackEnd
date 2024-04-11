package com.mindway.server.v2.domain.order.service.impl;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.order.service.BookRequestService;
import com.mindway.server.v2.domain.order.util.OrdersConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransaction
public class BookRequestServiceImpl implements BookRequestService {

    private final UserUtil userUtil;
    private final OrdersRepository ordersRepository;
    private final OrdersConverter ordersConverter;

    public void execute(OrderRequest bookRequest) {
        User user = userUtil.getCurrentUser();

        Orders order = ordersConverter.toEntity(bookRequest, user);
        ordersRepository.save(order);
    }
}
