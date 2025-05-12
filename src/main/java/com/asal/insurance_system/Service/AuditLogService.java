package com.asal.insurance_system.Service;

import com.asal.insurance_system.Model.AuditLog;
import com.asal.insurance_system.Repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public void logAction(String actionType, String tableName, Integer recordId, String oldValue, String newValue, Integer userId, String userType) {
        AuditLog log = new AuditLog();
        log.setActionType(actionType);
        log.setTableName(tableName);
        log.setRecordId(recordId);
        log.setActionTimestamp(LocalDateTime.now());
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        log.setUserId(userId);
        log.setUserType(userType);
        auditLogRepository.save(log);
    }

    public List<AuditLog> getAllAuditLogs(){
        return auditLogRepository.findAll();
    }
}
