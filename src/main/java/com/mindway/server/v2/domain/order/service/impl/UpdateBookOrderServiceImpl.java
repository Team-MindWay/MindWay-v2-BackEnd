package com.mindway.server.v2.domain.order.service.impl;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.order.exception.NotFoundOrderException;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderUpdate;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.order.service.UpdateBookOrderService;
import com.mindway.server.v2.domain.order.util.OrdersConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class UpdateBookOrderServiceImpl implements UpdateBookOrderService {
    private final UserUtil userUtil;
    private final OrdersRepository ordersRepository;

    @Override
    public void execute(Long id, OrderUpdate orderUpdate) {
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
