package com.mindway.server.v2.domain.rank.service.impl;

import com.mindway.server.v2.domain.rank.repository.RankRepository;
import com.mindway.server.v2.domain.rank.service.DeleteRankService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class DeleteRankServiceImpl implements DeleteRankService {

    private final RankRepository rankRepository;

    public void execute() {
        rankRepository.deleteAll();
    }
}
