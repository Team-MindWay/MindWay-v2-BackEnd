package com.mindway.server.v2.domain.user.service;

import com.mindway.server.v2.domain.user.presentation.dto.response.MyOrdersResponse;

import java.util.List;

public interface GetMyOrdersService {
    List<MyOrdersResponse> execute();
}
