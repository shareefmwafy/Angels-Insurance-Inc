package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Enum.AccidentStatus;
import com.asal.insurance_system.Model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class AccidentResponse {
    private Integer id;
    private LocalDate accidentDate;
    private String location;
    private String description;
    private AccidentStatus status;
    private List<String> documents;
    private Integer customerId;
}

