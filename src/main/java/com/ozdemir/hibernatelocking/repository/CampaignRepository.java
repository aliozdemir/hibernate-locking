package com.ozdemir.hibernatelocking.repository;

import com.ozdemir.hibernatelocking.model.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAll();
}
