package com.asal.insurance_system.Mapper;

import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    // map from Entity to DTO
    @Mapping(target = "password", ignore = true) // don't move the password from entity to DTO
    UserDTO mapToDto (User entity);

    // map from DTO to Entity
    User mapToEntity (UserDTO dto);

//    @Mapping(target = "authorities", ignore = true)
//    @Mapping(target = "id", ignore = true)
//    User mapToUpdatedUser (User user);

    @Mapping(target = "id", ignore = true) // Prevents changing the ID
    @Mapping(target = "authorities", ignore = true) // Prevents modifying authorities/roles
    void mapToUpdatedUser(User source, @MappingTarget User target);

}
