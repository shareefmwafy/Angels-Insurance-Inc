package com.asal.insurance_system.Service;

import com.asal.insurance_system.Enum.EnumCancellationRequestStatus;
import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.CancellationRequest;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Repository.CancellationRequestRepository;
import com.asal.insurance_system.Repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancellationRequestService {
    private final CancellationRequestRepository cancellationRequestRepository;
    private final PolicyRepository policyRepository;

    public CancellationRequestService(CancellationRequestRepository cancellationRequestRepository, PolicyRepository policyRepository) {
        this.cancellationRequestRepository = cancellationRequestRepository;
        this.policyRepository = policyRepository;
    }

    public boolean requestPolicyCancellation(Integer policyId, String reason, Integer customerId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
        if(policy.getCustomer().getId() == customerId){
            Optional<CancellationRequest> cancellationRequestInDb = cancellationRequestRepository.findByPolicyId(policyId);
            if(cancellationRequestInDb.isPresent()){
                return false;
            }

            CancellationRequest request = new CancellationRequest();
            request.setPolicy(policy);
            request.setReason(reason);

            cancellationRequestRepository.save(request);
            return true;
        }
        return false;
    }

    public void approveCancellationRequest(Integer requestId) {
        CancellationRequest request = cancellationRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("This Policy Request Not Found"));

        Policy policy = request.getPolicy();
        policy.setPolicyStatus(EnumPolicyStatus.CANCELED);

        request.setStatus(EnumCancellationRequestStatus.APPROVED);

        policyRepository.save(policy);
        cancellationRequestRepository.save(request);
    }


    public List<CancellationRequest> getAllCancellationRequests() {
        return cancellationRequestRepository.findAll();
    }
}
