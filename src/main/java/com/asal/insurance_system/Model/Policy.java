package com.asal.insurance_system.Model;


import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Enum.EnumPolicyType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Integer policyId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type")
    private EnumPolicyType policyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_status")
    private EnumPolicyStatus policyStatus;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
}
