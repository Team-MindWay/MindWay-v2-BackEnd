package com.mindway.server.v2.domain.rank.scheduler;

import com.mindway.server.v2.domain.rank.service.DeleteRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class RankScheduler {

    private final DeleteRankService deleteRankService;

    @Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Seoul")
    public void run() {
        deleteRankService.execute();
    }
}
