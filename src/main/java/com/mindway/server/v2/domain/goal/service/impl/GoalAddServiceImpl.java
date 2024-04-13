package com.mindway.server.v2.domain.goal.service.impl;

import com.mindway.server.v2.domain.goal.entity.Goal;
import com.mindway.server.v2.domain.goal.exception.ExistAlreadyGoalException;
import com.mindway.server.v2.domain.goal.presentation.dto.request.GoalAddRequestDto;
import com.mindway.server.v2.domain.goal.repository.GoalRepository;
import com.mindway.server.v2.domain.goal.service.GoalAddService;
import com.mindway.server.v2.domain.goal.util.GoalConverter;
import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.domain.user.util.UserUtil;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;

@ServiceWithTransaction
@RequiredArgsConstructor
public class GoalAddServiceImpl implements GoalAddService {

    private final GoalRepository goalRepository;
    private final UserUtil userUtil;
    private final GoalConverter goalConverter;

    public void execute(GoalAddRequestDto goalAddRequestDto) {
        User user = userUtil.getCurrentUser();

        if (goalRepository.existsByUser(user))
            throw new ExistAlreadyGoalException();

        Goal goal = goalConverter.toEntity(user, getCurMonday(), getCurSunday(), goalAddRequestDto.getGoal_count());

        goalRepository.save(goal);
    }

    private String getCurMonday(){
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        return formatter.format(c.getTime());
    }

    private String getCurSunday(){
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        c.add(c.DATE,7);
        return formatter.format(c.getTime());
    }


}
