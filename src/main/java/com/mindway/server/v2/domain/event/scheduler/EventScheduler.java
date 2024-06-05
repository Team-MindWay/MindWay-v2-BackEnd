package com.mindway.server.v2.domain.event.scheduler;

import com.mindway.server.v2.domain.event.service.ChangeEventStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final ChangeEventStatusService changeEventStatusService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void changeEventStatus() {
        changeEventStatusService.execute();
    }
}
