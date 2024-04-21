package com.mindway.server.v2.domain.rank.service;

import com.mindway.server.v2.domain.rank.presentation.dto.response.RankResponse;

import java.util.List;

public interface GetRankService {
    List<RankResponse> execute();
}
