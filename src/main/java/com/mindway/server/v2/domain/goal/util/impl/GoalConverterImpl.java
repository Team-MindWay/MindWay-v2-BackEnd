package com.mindway.server.v2.domain.goal.util.impl;

import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.goal.entity.Week;
import com.mindway.server.v2.domain.goal.presentation.dto.response.GoalGetResponse;
import com.mindway.server.v2.domain.goal.util.GoalConverter;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GoalConverterImpl implements GoalConverter {

    public Goal toEntity(User user, String created_at, String ended_at, Integer goal_count) {
        return Goal.builder()
                .started_at(LocalDate.parse(created_at))
                .ended_at(LocalDate.parse(ended_at))
                .goal_value(goal_count)
                .now_count(0)
                .week(new Week(0,0,0,0,0,0,0))
                .user(user)
                .build();
    }

    public GoalGetResponse toDto(Week week, Integer goal_value, Integer now_count) {
        return GoalGetResponse.builder()
                .mon(week.getMon())
                .tue(week.getTue())
                .wed(week.getWed())
                .thu(week.getThu())
                .fri(week.getFri())
                .sat(week.getSat())
                .sun(week.getSun())
                .goal_value(goal_value)
                .now_count(now_count)
                .build();
    }
}

