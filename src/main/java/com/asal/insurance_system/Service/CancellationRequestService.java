package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.Enum.EnumCancellationRequestStatus;
import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.CancellationRequest;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.CancellationRequestRepository;
import com.asal.insurance_system.Repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancellationRequestService {

    private final CancellationRequestRepository cancellationRequestRepository;
    private final PolicyRepository policyRepository;
    private final AuditLogService logService;

    public ApiResponse<CancellationRequest> requestPolicyCancellation(Integer policyId, String reason, Integer customerId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
        if(Objects.equals(policy.getCustomer().getId(), customerId)){
            Optional<CancellationRequest> cancellationRequestInDb = cancellationRequestRepository.findByPolicyId(policyId);
            if(cancellationRequestInDb.isPresent()){
                return new ApiResponse<>(
                        "You Already sent Cancellation Request for this Policy and the Status is "+ cancellationRequestInDb.get().getStatus(),
                        HttpStatus.CONFLICT.value()
                );
            }

            CancellationRequest request = new CancellationRequest();
            request.setRequestDate(LocalDate.now());
            request.setStatus(EnumCancellationRequestStatus.PENDING);
            request.setPolicy(policy);
            request.setReason(reason);

            cancellationRequestRepository.save(request);

            logService.logAction(
                    "Request Policy Cancellation",
                    "Policy Cancellation",
                    request.getId(),
                    "",
                    "",
                    customerId,
                    "CUSTOMER"
            );

            return new ApiResponse<>(
                    "Created Successfully",
                    HttpStatus.CREATED.value(),
                    request
            );
        }
        return new ApiResponse<>(
                "You cannot cancel someone else's policy.",
                HttpStatus.FORBIDDEN.value()
        );
    }

    public void approveCancellationRequest(Integer requestId, User userDetails) {
        CancellationRequest request = cancellationRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("This Policy Request Not Found"));

        Policy policy = request.getPolicy();
        policy.setPolicyStatus(EnumPolicyStatus.CANCELED);

        request.setStatus(EnumCancellationRequestStatus.APPROVED);

        policyRepository.save(policy);
        cancellationRequestRepository.save(request);

        logService.logAction(
                "Approve Cancellation Request",
                "Policy Cancellation",
                request.getId(),
                "PENDING",
                "APPROVED",
                userDetails.getId(),
                userDetails.getRole().name()
        );
    }


    public List<CancellationRequest> getAllCancellationRequests() {
        return cancellationRequestRepository.findAll();
    }
}
