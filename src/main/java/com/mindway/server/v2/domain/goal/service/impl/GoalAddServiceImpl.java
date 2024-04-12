package com.mindway.server.v2.domain.goal.service.impl;

import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.goal.presentation.dto.request.GoalAddRequestDto;
import com.mindway.server.v2.domain.goal.repository.GoalRepository;
import com.mindway.server.v2.domain.goal.service.GoalAddService;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@ServiceWithTransaction
@RequiredArgsConstructor
public class GoalAddServiceImpl implements GoalAddService {

    private final GoalRepository goalRepository;
    private final UserUtil userUtil;

    public void execute(GoalAddRequestDto goalAddRequestDto) {
        User user = userUtil.getCurrentUser();

        long now = new Date().getTime();
        Date goalExpired = new Date(now + 604800000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String goalStartedDate = dateFormat.format(now);
        String goalExpiredDate = dateFormat.format(goalExpired);

        Goal goal = Goal.builder()
                .started_at(LocalDate.parse(goalStartedDate))
                .ended_at(LocalDate.parse(goalExpiredDate))
                .goal_count(goalAddRequestDto.getGoal_count())
                .user(user)
                .build();

        goalRepository.save(goal);
    }
}
