package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // map from Entity to DTO
    @Mapping(target = "password", ignore = true) // don't move the password from entity to DTO
    UserDTO mapToDto (User entity);

    // map from DTO to Entity
    User mapToEntity (UserDTO dto);
}
