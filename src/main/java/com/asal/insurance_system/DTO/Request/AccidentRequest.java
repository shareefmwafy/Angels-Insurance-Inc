package com.asal.insurance_system.DTO.Request;

import com.asal.insurance_system.Enum.AccidentStatus;
import com.asal.insurance_system.Model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class AccidentRequest {
    private LocalDate accidentDate;
    private String location;
    private String description;
    private AccidentStatus status;
    private List<String> documents;
    private Integer customerId;

    public void setAccidentDate(LocalDate accidentDate) {
        this.accidentDate = accidentDate;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(AccidentStatus status) {
        this.status = status;
    }
    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
}
