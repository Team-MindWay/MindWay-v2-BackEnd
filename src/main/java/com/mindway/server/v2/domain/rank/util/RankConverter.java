package com.mindway.server.v2.domain.rank.util;

import com.mindway.server.v2.domain.rank.entity.Ranks;
import com.mindway.server.v2.domain.rank.presentation.dto.response.RankResponse;

public interface RankConverter {
    RankResponse toDto (Ranks rank);
}
