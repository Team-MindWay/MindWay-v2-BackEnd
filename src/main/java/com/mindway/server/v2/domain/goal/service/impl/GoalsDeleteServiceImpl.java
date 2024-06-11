package com.mindway.server.v2.domain.goal.service.impl;

import com.mindway.server.v2.domain.goal.repository.GoalRepository;
import com.mindway.server.v2.domain.goal.service.GoalsDeleteService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ServiceWithTransaction
@RequiredArgsConstructor
@Slf4j
public class GoalsDeleteServiceImpl implements GoalsDeleteService {

    private final GoalRepository goalRepository;

    public void execute() {
        log.info("===================목표 초기화 스케줄러 시작===================");
        goalRepository.deleteAll();
        log.info("===================목표 초기화 스케줄러 끝===================");
    }

}
