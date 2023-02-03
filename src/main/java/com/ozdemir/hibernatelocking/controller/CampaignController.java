package com.ozdemir.hibernatelocking.controller;

import com.ozdemir.hibernatelocking.model.entity.Campaign;
import com.ozdemir.hibernatelocking.model.response.CampaignsResponse;
import com.ozdemir.hibernatelocking.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    @Operation( summary = "This service is available to users who has ADMIN role")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Campaign campaign){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        campaignService.save(campaign);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "This service is available to users who has ADMIN role")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        campaignService.update(id);
        return ResponseEntity.ok(null);
    }


    @Operation(summary = "This service is available to users who has USER and ADMIN role")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/campaigns")
    public ResponseEntity<List<Campaign>> listOfCampaigns(){
        return ResponseEntity.ok(campaignService.getCampaigns(0));
    }
}
