package com.asal.insurance_system.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "action_timestamp")
    private LocalDateTime actionTimestamp;

    @Column(name = "old_value", columnDefinition = "TEXT")
    private String oldValue;

    @Column(name = "new_value", columnDefinition = "TEXT")
    private String newValue;

    @Column(name = "user_id")
    private Integer userId;

    public AuditLog() {
    }

    public AuditLog(Integer id, String actionType, String tableName, Integer recordId, String userType, LocalDateTime actionTimestamp) {
        Id = id;
        this.actionType = actionType;
        this.tableName = tableName;
        this.recordId = recordId;
        this.userType = userType;
        this.actionTimestamp = actionTimestamp;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public void setUserName(String userType) {
        this.userType = userType;
    }

    public void setActionTimestamp(LocalDateTime actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
