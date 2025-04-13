package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.ClaimRequest;
import com.asal.insurance_system.DTO.Request.ClaimStatusRequest;
import com.asal.insurance_system.DTO.Response.ClaimResponse;
import com.asal.insurance_system.Enum.EnumClaimStatus;
import com.asal.insurance_system.Mapper.ClaimMapper;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.Claim;
import com.asal.insurance_system.Model.Customer;
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


    public ClaimsService(ClaimRepository claimRepository, ClaimMapper claimMapper, CustomerRepository customerRepository, AccidentRepository accidentRepository){
        this.claimMapper = claimMapper;
        this.claimRepository = claimRepository;
        this.customerRepository = customerRepository;
        this.accidentRepository = accidentRepository;
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
        return claimMapper.mapToResponseDto(claim);
    }

    public List<ClaimResponse> getClaimsByCustomer(Integer customerId){
        List<Claim> claims = claimRepository.findByCustomerId(customerId);
        return claims.stream()
                .map(claimMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
    public ClaimResponse updateClaimStatus(Integer claimId, ClaimStatusRequest claimStatusRequest){
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

        claim.setStatus(statusEnum);
        claim.setAmountApproved(claimStatusRequest.getAmountApproved());
        claimRepository.save(claim);
        return claimMapper.mapToResponseDto(claim);
    }
}
