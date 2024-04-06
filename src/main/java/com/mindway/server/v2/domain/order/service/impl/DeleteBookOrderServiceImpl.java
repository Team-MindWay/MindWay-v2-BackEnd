package com.mindway.server.v2.domain.order.service.impl;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.order.exception.NotFoundOrderException;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.order.service.DeleteBookOrderService;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class DeleteBookOrderServiceImpl implements DeleteBookOrderService {
    private final OrdersRepository ordersRepository;
    private final UserUtil userUtil;

    @Override
    public void execute(Long id) {
        User user = userUtil.getCurrentUser();

        Orders order = ordersRepository.findById(id)
                .orElseThrow(NotFoundOrderException::new);

        if (user.getAuthority() != Authority.ROLE_TEACHER
                && user.getAuthority() != Authority.ROLE_HELPER) {
            if (user != order.getUser())
                throw new NotAccessStudentException();
        }

        ordersRepository.delete(order);
    }
}
