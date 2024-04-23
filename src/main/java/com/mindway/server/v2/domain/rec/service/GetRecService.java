package com.mindway.server.v2.domain.rec.service;

import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.response.RecInfoResponse;

import java.util.List;

public interface GetRecService {
    List<RecInfoResponse> execute(Type type);
}
