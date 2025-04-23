package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.CancellationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancellationRequestRepository extends JpaRepository<CancellationRequest, Integer> {
    Optional<CancellationRequest> findByPolicyId(Integer policyId);
}
