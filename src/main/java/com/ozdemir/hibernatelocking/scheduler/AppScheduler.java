package com.ozdemir.hibernatelocking.scheduler;

import com.ozdemir.hibernatelocking.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppScheduler {

    private final TestService testService;

    public AppScheduler(TestService testService) {
        this.testService = testService;
    }

    // cron = "second minute hour dayOfMonth dayOfWeek year"
    // everyday executes at 22:10
    @Scheduled(cron = "0 18 22 * * *")
    public void campaignInfo(){
        testService.writeValidCampaigns();
    }
}
