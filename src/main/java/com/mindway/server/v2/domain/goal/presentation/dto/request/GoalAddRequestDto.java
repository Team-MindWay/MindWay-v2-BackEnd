package com.mindway.server.v2.domain.goal.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoalAddRequestDto {
    @NotNull
    private Long goal_count;
}
