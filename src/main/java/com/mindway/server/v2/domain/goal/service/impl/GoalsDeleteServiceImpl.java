package com.mindway.server.v2.domain.goal.service.impl;

import com.mindway.server.v2.domain.goal.repository.GoalRepository;
import com.mindway.server.v2.domain.goal.service.GoalsDeleteService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithTransaction
@RequiredArgsConstructor
public class GoalsDeleteServiceImpl implements GoalsDeleteService {

    private final GoalRepository goalRepository;

    public void execute() {
        goalRepository.deleteAll();
    }

}
