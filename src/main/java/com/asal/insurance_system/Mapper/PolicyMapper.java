package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.Request.PolicyRequestDTO;
import com.asal.insurance_system.DTO.Response.PolicyResponseDTO;
import com.asal.insurance_system.Model.Policy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PolicyMapper {


    void policyToRequestDto(PolicyRequestDTO dto, @MappingTarget Policy entity);

    void entityToDtoResponse(Policy entity, @MappingTarget PolicyResponseDTO dto);

    PolicyResponseDTO entityToDtoResponse(Policy entity);
}
