package com.asal.insurance_system.Components;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
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
                log.info("Policy ID {} has been marked as EXPIRED ", policy.getId());
            }
            log.info("Policy ID: {}", policy.getId());
            log.info("EndDate: {}", policy.getEndDate());
            log.info("Today: {}", today);
            log.info("Status: {}", policy.getPolicyStatus());
        }
    }

}
