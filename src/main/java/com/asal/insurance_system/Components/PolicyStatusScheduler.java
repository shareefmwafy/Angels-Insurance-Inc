package com.asal.insurance_system.Components;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@Slf4j
public class PolicyStatusScheduler {

    private final PolicyRepository policyRepository;
    public PolicyStatusScheduler(PolicyRepository policyRepository){
        this.policyRepository = policyRepository;
    }


    @Scheduled(cron = "${scheduling.cron.policy-status-update}")
    public void updatePolicyStatuses(){
        LocalDate today = LocalDate.now();
        List<Policy> allPolicies = policyRepository.findAll();


        for (Policy policy : allPolicies){
            if(policy.getEndDate().isBefore(today) && policy.getPolicyStatus() == EnumPolicyStatus.ACTIVE){
                policy.setPolicyStatus(EnumPolicyStatus.EXPIRED);
                policyRepository.save(policy);
                log.info("Policy ID {} has been marked as EXPIRED" , policy.getPolicyId());
            }
            log.info("Policy ID: {}", policy.getPolicyId());
            log.info("EndDate: {}", policy.getEndDate());
            log.info("Today: {}", today);
            log.info("Status: {}", policy.getPolicyStatus());
        }
    }

}
