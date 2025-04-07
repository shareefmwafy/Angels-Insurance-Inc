package com.asal.insurance_system.Components;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Repository.PolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PolicyStatusScheduler {

    private final PolicyRepository policyRepository;

    private Logger logger = LoggerFactory.getLogger(PolicyStatusScheduler.class);
    @Autowired
    public PolicyStatusScheduler(PolicyRepository policyRepository){
        this.policyRepository = policyRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updatePolicyStatuses(){
        LocalDate today = LocalDate.now();
        List<Policy> allPolicies = policyRepository.findAll();


        for (Policy policy : allPolicies){
            if(policy.getEndDate().isBefore(today) && policy.getPolicyStatus() == EnumPolicyStatus.ACTIVE){
                policy.setPolicyStatus(EnumPolicyStatus.EXPIRED);
                policyRepository.save(policy);
                logger.info("Policy ID " + policy.getId() + " has been marked as EXPIRED");
            }
            System.out.println("Policy ID: " + policy.getId());
            System.out.println("EndDate: " + policy.getEndDate());
            System.out.println("Today: " + today);
            System.out.println("Status: " + policy.getPolicyStatus());

        }
    }

}
