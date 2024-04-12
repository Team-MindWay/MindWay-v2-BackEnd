package com.mindway.server.v2.domain.goal.service;

import com.mindway.server.v2.domain.goal.presentation.dto.request.GoalAddRequestDto;

public interface GoalAddService {
    void execute(GoalAddRequestDto goalAddRequestDto);
}
