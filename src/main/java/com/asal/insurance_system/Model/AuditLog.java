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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "action_timestamp")
    private LocalDateTime actionTimestamp;

}
