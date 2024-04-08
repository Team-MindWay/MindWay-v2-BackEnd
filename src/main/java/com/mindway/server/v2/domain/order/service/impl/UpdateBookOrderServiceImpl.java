package com.mindway.server.v2.domain.order.service.impl;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.order.exception.NotFoundOrderException;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderUpdateRequest;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.order.service.UpdateBookOrderService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransaction
public class UpdateBookOrderServiceImpl implements UpdateBookOrderService {
    private final UserUtil userUtil;
    private final OrdersRepository ordersRepository;

    public void execute(Long id, OrderUpdateRequest orderUpdate) {
        User user = userUtil.getCurrentUser();
        Orders order = ordersRepository.findById(id)
                .orElseThrow(NotFoundOrderException::new);

        if (user != order.getUser()){
            throw new NotAccessStudentException();
        }

        order.updateOrder(orderUpdate);

        ordersRepository.save(order);
    }
}
