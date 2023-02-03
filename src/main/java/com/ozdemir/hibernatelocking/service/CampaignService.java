package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.model.entity.Campaign;
import com.ozdemir.hibernatelocking.model.response.CampaignsResponse;
import com.ozdemir.hibernatelocking.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public void save(Campaign campaign){
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(campaign);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            log.error("Save campaign exception ", e);
        }
    }

    @Transactional
    public void update(Long id){
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Campaign campaign = entityManager.find(Campaign.class, id);
            try{
                //Optimistic lock hatasının alınabilmesi için sleep esnasında db den değeri değiştirmek gerekiyor
                Thread.sleep(10000);
            }catch (Exception e){
                log.error("Thread exception");
            }
            campaign.setCodeCount(campaign.getCodeCount() - 1);
            entityManager.getTransaction().begin();
            entityManager.merge(campaign);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(e instanceof RollbackException){
                log.error("Optimistic lock exception occurred", e.getMessage());
            }else{
                log.error("Exception occurred", e.getMessage());
            }
        }
    }

    public void writeValidCampaigns(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Campaign> campaigns = entityManager.createQuery("select c from Campaign c").getResultList();
        campaigns.forEach(c->log.info(c.toString()));
    }

    public List<Campaign> getCampaigns(long delay) {
        log.warn("CAMPAIGN LIST SERVICE IS EXECUTING!!!!!!");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.warn("CAMPAIGN LIST SERVICE IS EXECUTED!!!!!!");
        return campaignRepository.findAll();

    }
}
