package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Policy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

public class PolicyResponseDTO {

    private Integer policyId;
    private EnumPolicyType policyType;
    private EnumPolicyStatus policyStatus;
    private Float amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer customerId;

    public PolicyResponseDTO(Policy policy) {
        this.policyId = policy.getId();
        this.policyType = policy.getPolicyType();
        this.policyStatus = policy.getPolicyStatus();
        this.amount = policy.getAmount();
        this.startDate = policy.getStartDate();
        this.endDate = policy.getEndDate();
        this.customerId = policy.getCustomer().getId();
    }

    public PolicyResponseDTO(){}

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public EnumPolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(EnumPolicyType policyType) {
        this.policyType = policyType;
    }

    public EnumPolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(EnumPolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
