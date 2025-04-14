package com.asal.insurance_system.Service;

import com.asal.insurance_system.Model.AuditLog;
import com.asal.insurance_system.Repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logAction(String actionType, String tableName, Integer recordId, String userName, String oldValue, String newValue) {
        AuditLog log = new AuditLog();
        log.setActionType(actionType);
        log.setTableName(tableName);
        log.setRecordId(recordId);
        log.setUserName(userName);
        log.setActionTimestamp(LocalDateTime.now());
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        auditLogRepository.save(log);
    }
}
