package com.mindway.server.v2.domain.rank.util.impl;

import com.mindway.server.v2.domain.rank.entity.Ranks;
import com.mindway.server.v2.domain.rank.presentation.dto.response.RankResponse;
import com.mindway.server.v2.domain.rank.util.RankConverter;
import org.springframework.stereotype.Component;

@Component
public class RankConverterImpl implements RankConverter {

    public RankResponse toDto(Ranks rank) {
        return RankResponse.builder()
                .name(rank.getUser().getName())
                .accrue(rank.getAccrue())
                .build();
    }
}
