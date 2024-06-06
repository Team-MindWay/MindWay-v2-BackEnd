package com.mindway.server.v2.domain.user.service.impl;

import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.presentation.dto.response.MyOrdersResponse;
import com.mindway.server.v2.domain.user.service.GetMyOrdersService;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithReadOnlyTransaction
public class GetMyOrdersServiceImpl implements GetMyOrdersService {

    private final UserUtil userUtil;
    private final OrdersRepository ordersRepository;

    public List<MyOrdersResponse> execute() {
        User user = userUtil.getCurrentUser();
        List<Orders> ordersList = ordersRepository.findByUser(user);

        return ordersList.stream()
                .map(orders -> MyOrdersResponse.builder()
                        .id(orders.getId())
                        .title(orders.getTitle())
                        .author(orders.getAuthor())
                        .book_url(orders.getBookURL())
                        .build())
                .collect(Collectors.toList());
    }
}
