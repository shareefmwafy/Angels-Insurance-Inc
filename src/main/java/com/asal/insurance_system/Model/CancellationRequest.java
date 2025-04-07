package com.asal.insurance_system.Model;

import com.asal.insurance_system.Enum.EnumCancellationRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class CancellationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Enumerated(EnumType.STRING)
    private EnumCancellationRequestStatus status = EnumCancellationRequestStatus.PENDING;

    private LocalDate requestDate = LocalDate.now();

    private String reason;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public void setStatus(EnumCancellationRequestStatus status) {
        this.status = status;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

