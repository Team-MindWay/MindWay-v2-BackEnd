package com.mindway.server.v2.domain.event.scheduler;

import com.mindway.server.v2.domain.event.service.ChangeNowToPastService;
import com.mindway.server.v2.domain.event.service.ChangePendingToNowService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final ChangeNowToPastService changeNowToPastService;
    private final ChangePendingToNowService changePendingToNowService;

    @Scheduled(cron = "0 0 0 * *", zone = "Asia/Seoul")
    public void changeNowToPast() {
        changeNowToPastService.execute();
    }

    @Scheduled(cron = "0 0 0 * *", zone = "Asia/Seoul")
    public void changePendingToNow() {
        changePendingToNowService.execute();
    }

}
