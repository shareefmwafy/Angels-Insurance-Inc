package com.asal.insurance_system.Model;


import com.asal.insurance_system.Enum.EnumClaimStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer Id;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumClaimStatus status;

    @Column(name = "amount_requested")
    private Float amountRequested;

    @Column(name = "description")
    private String description;


    @Column(name = "amount_approved")
    private Float amountApproved;

    @Column(name = "supporting_document")
    private String supportingDocument;


    public void setClaimId(Integer claimId) {
        this.Id = claimId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(EnumClaimStatus status) {
        this.status = status;
    }

    public void setAmountRequested(Float amountRequested) {
        this.amountRequested = amountRequested;
    }

    public void setAmountApproved(Float amountApproved) {
        this.amountApproved = amountApproved;
    }

    public void setSupportingDocument(String supportingDocument) {
        this.supportingDocument = supportingDocument;
    }
}
