package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.PolicyRequestDTO;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Repository.CustomerRepository;
import com.asal.insurance_system.Repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PolicyService {

    @Autowired
    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(PolicyService.class);

    @Autowired
    public PolicyService(PolicyRepository policyRepository, CustomerRepository customerRepository) {
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
    }

    public Policy createPolicy(PolicyRequestDTO dto) {
        logger.info("Attempting to create policy for customerId: {}", dto.getCustomerId());

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> {
                    logger.error("Customer not found with ID: {}", dto.getCustomerId());
                    return new ResourceNotFoundException("Customer not found with ID " + dto.getCustomerId());
                });

        logger.info("Found customer: {} {}", customer.getFirstName(), customer.getLastName());

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setPolicyType(dto.getPolicyType());
        policy.setPolicyStatus(dto.getPolicyStatus());
        policy.setAmount(dto.getAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());

        Policy savedPolicy = policyRepository.save(policy);

        logger.info("Policy created successfully with ID: {}", savedPolicy.getPolicyId());

        return savedPolicy;
    }
}
