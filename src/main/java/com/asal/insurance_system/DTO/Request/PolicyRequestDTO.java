package com.asal.insurance_system.DTO.Request;

import com.asal.insurance_system.Enum.EnumPolicyStatus;
import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Model.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;

public class PolicyRequestDTO {

    private Integer customerId;

    private EnumPolicyType policyType;

    private EnumPolicyStatus policyStatus;

    private Float amount;

    private LocalDate startDate;

    private LocalDate endDate;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
