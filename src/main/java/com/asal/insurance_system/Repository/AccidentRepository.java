package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.Accident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentRepository extends JpaRepository<Accident, Integer> {
}
