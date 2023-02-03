package com.ozdemir.hibernatelocking.service.async;

import com.ozdemir.hibernatelocking.aspect.CustomLog;
import com.ozdemir.hibernatelocking.model.entity.Campaign;
import com.ozdemir.hibernatelocking.model.response.AsyncResponse;
import com.ozdemir.hibernatelocking.model.response.UserResponse;
import com.ozdemir.hibernatelocking.service.CampaignService;
import com.ozdemir.hibernatelocking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncService {

    @Value("${async.user.delay}")
    private long userDelay;

    @Value("${async.campaign.delay}")
    private long campaignDelay;

    private final UserService userService;

    private final CampaignService campaignService;

    @CustomLog(level = LogLevel.WARN)
    public AsyncResponse test(){

        AtomicReference<List<UserResponse>> userResult = new AtomicReference<List<UserResponse>>();
        AtomicReference<List<Campaign>> campaignResult = new AtomicReference<List<Campaign>>();

        final CompletableFuture<Void> firstFuture = CompletableFuture.supplyAsync(() -> userService.getUsers(userDelay, false)).thenAccept(userResult::set);
        final CompletableFuture<Void> secondFuture = CompletableFuture.supplyAsync(() -> campaignService.getCampaigns(campaignDelay)).thenAccept(campaignResult::set);

        try{
            Stream.of(firstFuture, secondFuture).map(CompletableFuture::join).collect(Collectors.toList());
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        /*all tasks completed. Total alapsed time in this example is about 10000ms
          If i didn't make async call it would take about 19000ms
         */
        final List<UserResponse> users = userResult.get();
        final List<Campaign> campaigns = campaignResult.get();

        return AsyncResponse.builder().users(users).campaigns(campaigns).build();
    }

}
