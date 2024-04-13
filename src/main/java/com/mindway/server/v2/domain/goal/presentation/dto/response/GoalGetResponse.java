package com.mindway.server.v2.domain.goal.presentation.dto.response;

import com.mindway.server.v2.domain.goal.entity.Week;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class GoalGetResponse {

    private Integer mon;

    private Integer tue;

    private Integer wed;

    private Integer thu;

    private Integer fri;

    private Integer sat;

    private Integer sun;

    private Integer now_count;

    private Integer goal_value;

}
