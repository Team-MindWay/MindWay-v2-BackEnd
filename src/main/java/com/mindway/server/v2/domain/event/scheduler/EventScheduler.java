package com.mindway.server.v2.domain.event.scheduler;

import com.mindway.server.v2.domain.event.service.ChangeNowToPastService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class EventScheduler {

    private final ChangeNowToPastService changeNowToPastService;

    @Scheduled(cron = "0 0 0 * *", zone = "Asia/Seoul")
    public void changeNowToPast() {
        changeNowToPastService.execute();
    }

}
