package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Enum.AccidentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class AccidentResponse {
    private Long id;
    private LocalDate accidentDate;
    private String location;
    private String description;
    private AccidentStatus status;
    private List<String> documents;
}

