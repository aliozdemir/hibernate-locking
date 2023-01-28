package com.ozdemir.hibernatelocking.config;

import com.ozdemir.hibernatelocking.model.entity.Campaign;
import com.ozdemir.hibernatelocking.model.entity.Role;
import com.ozdemir.hibernatelocking.repository.CampaignRepository;
import com.ozdemir.hibernatelocking.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AppStartup implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final CampaignRepository campaignRepository;
    private boolean alreadySetup = false;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        //Create roles
        Role role1 = new Role();
        role1.setName("USER");
        Role role2 = new Role();
        role2.setName("ADMIN");
        List<Role> listOfRole = List.of(role1, role2);
        roleRepository.saveAll(listOfRole);

        //Create campaigns
        Campaign campaign1 = Campaign.builder().name("x campaign").codeCount(15).build();
        Campaign campaign2 = Campaign.builder().name("y campaign").codeCount(45).build();
        campaignRepository.save(campaign1);
        campaignRepository.save(campaign2);

        alreadySetup = true;
    }
}
