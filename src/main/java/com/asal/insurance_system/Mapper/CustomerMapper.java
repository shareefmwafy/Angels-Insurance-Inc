package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer mapToEntity (CustomerDTO dto);

    CustomerDTO mapToDto (Customer entity);
}
