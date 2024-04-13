package com.mindway.server.v2.domain.goal.service.impl;

import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.goal.exception.NotExistGoalException;
import com.mindway.server.v2.domain.goal.presentation.dto.response.GoalGetResponse;
import com.mindway.server.v2.domain.goal.repository.GoalRepository;
import com.mindway.server.v2.domain.goal.service.GoalGetService;
import com.mindway.server.v2.domain.goal.util.GoalConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
@Slf4j
public class GoalGetServiceImpl implements GoalGetService {

    private final GoalRepository goalRepository;
    private final UserUtil userUtil;
    private final GoalConverter goalConverter;

    public GoalGetResponse execute() {
        User user = userUtil.getCurrentUser();

        Goal goal = goalRepository.findByUser(user)
                .orElseThrow(NotExistGoalException::new);

        return goalConverter.toDto(goal.getWeek(), goal.getGoal_value(), goal.getNow_count());
    }
}
