package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.DTO.Request.CustomerRequest;
import com.asal.insurance_system.DTO.Response.CustomerResponse;
import com.asal.insurance_system.Model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer mapToEntity (CustomerRequest dto);

    @Mapping(target = "authorities", ignore = true)
    void mapToUpdateCustomerRequest (@MappingTarget Customer customerInDb, CustomerRequest dto);

    CustomerResponse mapToCustomerResponse(Customer customer);
    CustomerDTO mapToDto (Customer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void mapToUpdatedCustomer (Customer source, @MappingTarget Customer target);
}
