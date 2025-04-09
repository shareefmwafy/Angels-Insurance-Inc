package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Enum.EnumClaimStatus;
import com.asal.insurance_system.Model.Customer;

public class ClaimResponse {

    private Integer Id;


    private Customer customer;


    private EnumClaimStatus status;


    private Float amountRequested;


    private String description;



    private Float amountApproved;



    private String supportingDocument;



    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCustomer() {
        return customer.getId();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(EnumClaimStatus status) {
        this.status = status;
    }

    public void setAmountRequested(Float amountRequested) {
        this.amountRequested = amountRequested;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmountApproved(Float amountApproved) {
        this.amountApproved = amountApproved;
    }

    public void setSupportingDocument(String supportingDocument) {
        this.supportingDocument = supportingDocument;
    }


}
