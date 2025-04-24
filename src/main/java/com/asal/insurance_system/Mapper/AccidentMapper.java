package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.DTO.Response.AccidentResponse;
import com.asal.insurance_system.Model.Accident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccidentMapper {

    @Mapping(source = "customer.id", target = "customerId")
    AccidentResponse mapToAccidentResponse (Accident entity);
    AccidentRequest mapToAccidentRequest(Accident entity);
    Accident mapToEntityAccident (AccidentResponse dto);
    Accident mapToEntityAccident (AccidentRequest dto);
}
