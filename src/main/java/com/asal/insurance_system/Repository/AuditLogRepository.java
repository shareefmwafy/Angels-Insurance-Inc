package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.AuditLog;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface AuditLogRepository extends JpaRepository<AuditLog,Integer> {
}
