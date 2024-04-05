package com.mindway.server.v2.domain.order.service.impl;

import com.mindway.server.v2.domain.order.entity.BookType;
import com.mindway.server.v2.domain.order.entity.Orders;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;
import com.mindway.server.v2.domain.order.repository.OrdersRepository;
import com.mindway.server.v2.domain.order.service.BookRequestService;
import com.mindway.server.v2.domain.order.util.OrdersConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class BookRequestServiceImpl implements BookRequestService {

    private final UserUtil userUtil;
    private final OrdersRepository ordersRepository;
    private final OrdersConverter ordersConverter;

    @Override
    public void execute(BookType bookType, OrderRequest bookRequest) {
        User user = userUtil.getCurrentUser();

        Orders order = ordersConverter.toEntity(bookRequest, user, bookType);
        ordersRepository.save(order);
    }
}
