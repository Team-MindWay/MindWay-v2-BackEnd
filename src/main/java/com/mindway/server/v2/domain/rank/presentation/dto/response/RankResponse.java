package com.mindway.server.v2.domain.rank.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RankResponse {
    private String name;
    private Integer accrue;
}
