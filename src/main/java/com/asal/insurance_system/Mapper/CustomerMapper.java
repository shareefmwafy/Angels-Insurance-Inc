package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer mapToEntity (CustomerDTO dto);

    CustomerDTO mapToDto (Customer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void mapToUpdatedCustomer (Customer source, @MappingTarget Customer target);
}
