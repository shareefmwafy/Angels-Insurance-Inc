package com.asal.insurance_system.Model;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Enum.EnumPolicyType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)  // Foreign key reference
    @JsonBackReference
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type")
    private EnumPolicyType policyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_status")
    private EnumPolicyStatus policyStatus;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Policy() {}

    public Policy(Integer Id, Customer customer, EnumPolicyType policyType, EnumPolicyStatus policyStatus, Float amount, LocalDate startDate, LocalDate endDate) {
        this.Id = Id;
        this.customer = customer;
        this.policyType = policyType;
        this.policyStatus = policyStatus;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Policy policy = (Policy) obj;
        return Objects.equals(Id, policy.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "Policy{" +
                "Id=" + Id +
                ", customer=" + (customer != null ? customer.getId() : "null") +
                ", policyType=" + policyType +
                ", policyStatus=" + policyStatus +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPolicyType(EnumPolicyType policyType) {
        this.policyType = policyType;
    }

    public void setPolicyStatus(EnumPolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
