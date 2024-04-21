package com.mindway.server.v2.domain.rank.presentation;

import com.mindway.server.v2.domain.rank.presentation.dto.response.RankResponse;
import com.mindway.server.v2.domain.rank.service.GetRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/rank")
public class RankController {
    private final GetRankService getRankService;

    @GetMapping
    public ResponseEntity<List<RankResponse>> getRank() {
        List<RankResponse> rank = getRankService.execute();
        return ResponseEntity.ok(rank);
    }
}
