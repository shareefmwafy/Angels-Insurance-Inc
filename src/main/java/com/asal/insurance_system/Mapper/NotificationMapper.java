package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.DTO.Response.NotificationResponse;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponse mapToDto (Notification entity);
}
