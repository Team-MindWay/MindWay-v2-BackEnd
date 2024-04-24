package com.mindway.server.v2.domain.rec.service.impl;

import com.mindway.server.v2.domain.rec.entity.Type;
import com.mindway.server.v2.domain.rec.presentation.dto.response.RecInfoResponse;
import com.mindway.server.v2.domain.rec.repository.RecRepository;
import com.mindway.server.v2.domain.rec.service.GetRecService;
import com.mindway.server.v2.domain.rec.util.RecConverter;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class GetRecServiceImpl implements GetRecService {

    private final RecRepository recRepository;
    private final RecConverter recConverter;

    public List<RecInfoResponse> execute(Type type) {
        return recRepository.findByType(type)
                .stream().map(recConverter::toDto)
                .collect(Collectors.toList());
    }
}
