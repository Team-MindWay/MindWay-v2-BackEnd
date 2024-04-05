package com.mindway.server.v2.domain.order.service;

import com.mindway.server.v2.domain.order.entity.BookType;
import com.mindway.server.v2.domain.order.presentation.dto.request.OrderRequest;

public interface BookRequestService {
    void execute(BookType bookType, OrderRequest bookRequest);
}
