package com.mindway.server.v2.domain.goal.scheduler;

import com.mindway.server.v2.domain.goal.service.GoalsDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoalScheduler {

    private final GoalsDeleteService goalsDeleteService;

    @Scheduled(cron = "0 0 0 * * MON", zone = "Asia/Seoul")
    public void deleteGoals() {
        goalsDeleteService.execute();
    }
}
