package com.asal.insurance_system.DTO.Request;

public class ClaimStatusRequest {
    private String status;
    private Float amountApproved;


    public String getStatus() {
        return status;
    }

    public Float getAmountApproved() {
        return amountApproved;
    }

    public void setAmountApproved(Float amountApproved) {
        this.amountApproved = amountApproved;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
