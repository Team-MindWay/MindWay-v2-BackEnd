package com.mindway.server.v2.domain.rank.service.impl;

import com.mindway.server.v2.domain.rank.entity.Ranks;
import com.mindway.server.v2.domain.rank.presentation.dto.response.RankResponse;
import com.mindway.server.v2.domain.rank.repository.RankRepository;
import com.mindway.server.v2.domain.rank.service.GetRankService;
import com.mindway.server.v2.domain.rank.util.RankConverter;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class GetRankServiceImpl implements GetRankService {

    private final RankRepository rankRepository;
    private final RankConverter rankConverter;

    public List<RankResponse> execute() {
        List<Ranks> ranks = rankRepository.findTop3ByOrderByAccrueDesc();

        return ranks.stream()
                .map(rankConverter::toDto)
                .collect(Collectors.toList());
    }
}
