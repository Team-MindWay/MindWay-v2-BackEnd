package com.mindway.server.v2.domain.order.service.impl;

import com.mindway.server.v2.domain.order.exception.NotAccessStudentException;
import com.mindway.server.v2.domain.order.presentation.dto.response.OrdersResponse;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.order.service.GetBookOrdersService;
import com.mindway.server.v2.domain.order.util.OrdersConverter;
import com.mindway.server.v2.domain.user.entity.Authority;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class GetBookOrdersServiceImpl implements GetBookOrdersService {
    private final UserUtil userUtil;
    private final OrdersRepository ordersRepository;
    private final OrdersConverter ordersConverter;

    @Override
    public List<OrdersResponse> execute() {
        User user = userUtil.getCurrentUser();

        if (user.getAuthority() != Authority.ROLE_TEACHER
                && user.getAuthority() != Authority.ROLE_HELPER) {
            throw new NotAccessStudentException();
        }

        return ordersRepository.findAll().stream()
                .map(ordersConverter::toDto)
                .collect(Collectors.toList());
    }
}
