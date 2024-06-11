package com.mindway.server.v2.domain.rank.service.impl;

import com.mindway.server.v2.domain.rank.repository.RankRepository;
import com.mindway.server.v2.domain.rank.service.DeleteRankService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ServiceWithTransaction
@RequiredArgsConstructor
@Slf4j
public class DeleteRankServiceImpl implements DeleteRankService {

    private final RankRepository rankRepository;

    public void execute() {
        log.info("===================랭킹 초기화 스케줄러 시작===================");
        rankRepository.deleteAll();
        log.info("===================랭킹 초기화 스케줄러 시작===================");
    }
}
