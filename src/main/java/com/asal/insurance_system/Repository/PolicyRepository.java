package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    List<Policy> findByCustomerId(Integer customerId);
}
