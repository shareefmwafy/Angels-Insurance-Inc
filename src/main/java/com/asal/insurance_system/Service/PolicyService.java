package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.PolicyRequestDTO;
import com.asal.insurance_system.DTO.Response.PolicyResponseDTO;
import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Mapper.PolicyMapper;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.CustomerRepository;
import com.asal.insurance_system.Repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PolicyService {

    @Autowired
    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    private PolicyMapper policyMapper;

    private static final Logger logger = LoggerFactory.getLogger(PolicyService.class);

    @Autowired
    private AuditLogService logService;

    @Autowired
    public PolicyService(PolicyRepository policyRepository, CustomerRepository customerRepository) {
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
    }

    public Policy createPolicy(PolicyRequestDTO dto, User userDetails) {
        logger.info("Attempting to create policy for customerId: {}", dto.getCustomerId());

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> {
                    logger.error("Customer not found with ID: {}", dto.getCustomerId());
                    return new ResourceNotFoundException("Customer not found with ID " + dto.getCustomerId());
                });

        logger.info("Found customer: {} {}", customer.getFirstName(), customer.getLastName());

        Policy policy = new Policy();
        policyMapper.policyToRequestDto(dto,policy);

        Policy savedPolicy = policyRepository.save(policy);

        logger.info("Policy created successfully with ID: {}", savedPolicy.getPolicyId());
        logService.logAction(
                "Create New Policy",
                "Policy",
                policy.getPolicyId(),
                "", "",
                userDetails.getId(),
                userDetails.getRole().name()
        );
        return savedPolicy;
    }

    public PolicyResponseDTO getPolicyById(Integer id){
        Policy policy = policyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Policy Not Found with Id " + id));

        PolicyResponseDTO responseDTO = new PolicyResponseDTO();

        policyMapper.entityToDtoResponse(policy, responseDTO);
        return responseDTO;
    }

    public List<PolicyResponseDTO> getAllPolices(){
        List<Policy> policies = policyRepository.findAll();
        return policies.stream()
                .map(PolicyResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Policy updatePolicy(int id, PolicyRequestDTO requestDTO){
        Policy policyInDb = policyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Policy Not Found"));
        policyMapper.policyToRequestDto(requestDTO,policyInDb);
        return policyRepository.save(policyInDb);
    }

    public boolean deletePolicy(int id, User userDetails){
        Policy policyInDb = policyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Policy Not Found"));
        Integer policyId = policyInDb.getPolicyId();
        policyRepository.delete(policyInDb);

        logService.logAction(
                "Delete Policy",
                "Policy",
                policyId,
                "","",
                userDetails.getId(),
                userDetails.getRole().name()
        );
        return true;
    }

}
