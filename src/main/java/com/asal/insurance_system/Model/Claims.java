package com.asal.insurance_system.Model;


import com.asal.insurance_system.Enum.EnumClaimStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "claims")
public class Claims {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer claimId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumClaimStatus status;

    @Column(name = "amount_requested")
    private Float amountRequested;

    @Column(name = "amount_approved")
    private Float amountApproved;

    @Column(name = "supporting_document")
    private String supportingDocument;


    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public EnumClaimStatus getStatus() {
        return status;
    }

    public void setStatus(EnumClaimStatus status) {
        this.status = status;
    }

    public Float getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(Float amountRequested) {
        this.amountRequested = amountRequested;
    }

    public Float getAmountApproved() {
        return amountApproved;
    }

    public void setAmountApproved(Float amountApproved) {
        this.amountApproved = amountApproved;
    }

    public String getSupportingDocument() {
        return supportingDocument;
    }

    public void setSupportingDocument(String supportingDocument) {
        this.supportingDocument = supportingDocument;
    }
}
