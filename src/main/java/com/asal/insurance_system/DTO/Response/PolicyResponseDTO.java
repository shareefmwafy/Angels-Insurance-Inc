package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Policy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

public class PolicyResponseDTO {

    private Integer id;
    private EnumPolicyType policyType;
    private EnumPolicyStatus policyStatus;
    private Float amount;
    private LocalDate startDate;
    private LocalDate endDate;

    public PolicyResponseDTO(Policy policy) {
        this.id = policy.getId();
        this.policyType = policy.getPolicyType();
        this.policyStatus = policy.getPolicyStatus();
        this.amount = policy.getAmount();
        this.startDate = policy.getStartDate();
        this.endDate = policy.getEndDate();
    }

    public PolicyResponseDTO(){}

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
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


}
