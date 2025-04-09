package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.Request.ClaimRequest;
import com.asal.insurance_system.DTO.Response.ClaimResponse;
import com.asal.insurance_system.Model.Claim;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClaimMapper {

    Claim mapToEntity (ClaimRequest dto);

    ClaimResponse mapToResponseDto (Claim entity);
}
