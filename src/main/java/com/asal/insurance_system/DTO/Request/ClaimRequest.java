package com.asal.insurance_system.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClaimRequest {
    private Float amountRequested;
    private String description;
    private String supportingDocument;

    @NotNull(message = "accidentId is mandatory")
    private Integer accidentId;

    public void setAmountRequested(Float amountRequested) {
        this.amountRequested = amountRequested;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public void setSupportingDocument(String supportingDocument) {
        this.supportingDocument = supportingDocument;
    }

    public Float getAmountRequested() {
        return amountRequested;
    }

    public String getDescription() {
        return description;
    }

    public String getSupportingDocument() {
        return supportingDocument;
    }

    public Integer getAccidentId() {
        return accidentId;
    }
}
