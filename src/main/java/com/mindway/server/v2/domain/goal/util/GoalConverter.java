package com.mindway.server.v2.domain.goal.util;

import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.goal.entity.Week;
import com.mindway.server.v2.domain.goal.presentation.dto.response.GoalGetResponse;
import com.mindway.server.v2.domain.user.entity.User;

import java.time.LocalDate;

public interface GoalConverter {
    Goal toEntity(User user, String created_at, String ended_at, Integer goal_count);
    GoalGetResponse toDto(Week week, Integer goal_value, Integer now_count);
}
