package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    List<Claim> findByCustomerId(Integer customerId);
}
