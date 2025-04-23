package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.ClaimRequest;
import com.asal.insurance_system.DTO.Request.ClaimStatusRequest;
import com.asal.insurance_system.DTO.Response.ClaimResponse;
import com.asal.insurance_system.Enum.EnumClaimStatus;
import com.asal.insurance_system.Mapper.ClaimMapper;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.Claim;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.AccidentRepository;
import com.asal.insurance_system.Repository.ClaimRepository;
import com.asal.insurance_system.Repository.CustomerRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimsService {
    private final ClaimRepository claimRepository;
    private final ClaimMapper claimMapper;
    private final CustomerRepository customerRepository;
    private final AccidentRepository accidentRepository;
    private final AuditLogService logService;


    public ClaimsService(ClaimRepository claimRepository, ClaimMapper claimMapper, CustomerRepository customerRepository, AccidentRepository accidentRepository, AuditLogService logService){
        this.claimMapper = claimMapper;
        this.claimRepository = claimRepository;
        this.customerRepository = customerRepository;
        this.accidentRepository = accidentRepository;
        this.logService = logService;
    }

    public ClaimResponse createNewClaim(Integer customerId, ClaimRequest claimRequest, Integer customerLoggedInId) {
        if (!customerId.equals(customerLoggedInId)) {
            throw new IllegalAccessError("You can't create a claim for another customer");
        }

        Claim claim = claimMapper.mapToEntity(claimRequest);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new UsernameNotFoundException("Customer Not Found"));
        Accident accident = accidentRepository.findById(claimRequest.getAccidentId())
                        .orElseThrow(()-> new RuntimeException("Accident Not Found"));

        claim.setCustomer(customer);
        claim.setStatus(EnumClaimStatus.PENDING);
        claim.setAccident(accident);
        claimRepository.save(claim);

        logService.logAction(
                "Create New Claim",
                "Claims",
                claim.getId(),
                "",
                "",
                customerId,
                customer.getRole().name()
        );
        return claimMapper.mapToResponseDto(claim);
    }

    public List<ClaimResponse> getClaimsByCustomer(Integer customerId){
        List<Claim> claims = claimRepository.findByCustomerId(customerId);
        return claims.stream()
                .map(claimMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
    public ClaimResponse updateClaimStatus(Integer claimId, ClaimStatusRequest claimStatusRequest, User userDetails){
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(()-> new RuntimeException("Claim Not Found"));

        EnumClaimStatus statusEnum;
        try {
            statusEnum = EnumClaimStatus.valueOf(claimStatusRequest.getStatus());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid claim status value");
        }
        if (claim.getStatus() == EnumClaimStatus.COMPLETED || claim.getStatus() == EnumClaimStatus.REJECTED) {
            throw new IllegalStateException("Cannot update claim status as it's already finalized.");
        }
        String oldStatusClaim = claim.getStatus().name();
        claim.setStatus(statusEnum);
        claim.setAmountApproved(claimStatusRequest.getAmountApproved());
        claimRepository.save(claim);

        logService.logAction(
                "Update Claim Status",
                "Claims",
                claim.getId(),
                oldStatusClaim,
                statusEnum.name(),
                userDetails.getId(),
                userDetails.getRole().name()
        );

        return claimMapper.mapToResponseDto(claim);
    }
}
