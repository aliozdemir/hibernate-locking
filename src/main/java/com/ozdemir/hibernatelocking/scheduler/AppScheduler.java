package com.ozdemir.hibernatelocking.scheduler;

import com.ozdemir.hibernatelocking.service.CampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppScheduler {

    private final CampaignService campaignService;

    public AppScheduler(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    // cron = "second minute hour dayOfMonth dayOfWeek year"
    // everyday executes at 22:10
    @Scheduled(cron = "0 18 22 * * *")
    public void campaignInfo(){
        campaignService.writeValidCampaigns();
    }
}
