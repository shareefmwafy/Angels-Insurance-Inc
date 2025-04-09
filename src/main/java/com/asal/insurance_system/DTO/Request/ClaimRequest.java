package com.asal.insurance_system.DTO.Request;

public class ClaimRequest {
    private Float amountRequested;
    private String description;
    private String supportingDocument;

    public Float getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(Float amountRequested) {
        this.amountRequested = amountRequested;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupportingDocument() {
        return supportingDocument;
    }

    public void setSupportingDocument(String supportingDocument) {
        this.supportingDocument = supportingDocument;
    }
}
