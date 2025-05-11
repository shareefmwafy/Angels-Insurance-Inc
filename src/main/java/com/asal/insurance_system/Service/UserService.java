package com.asal.insurance_system.Service;

import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Mapper.UserMapper;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public ResponseEntity<Object> addUser(UserDTO userDTO)
    {
        try{
            if(isUserExist(userDTO.getEmail()))
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new AuthenticationResponse(
                        "this User Already Exist!",
                        HttpStatus.CONFLICT.value()
                    )
                );

            }
            User user = userMapper.mapToEntity(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            log.info("JWT Token generated successfully");
            log.info("Successfully Created new user with Role: "+ user.getRole());

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new AuthenticationResponse(
                        "User Created Successfully",
                    user.getId(),
                    HttpStatus.CREATED.value(),
                    jwtToken,
                    user.getRole()
                )
            );
        }
        catch (Exception e){
            log.error("User wasn't Added: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new AuthenticationResponse(
                    "Error occurred while adding user: "+e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            );
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getUserById(Integer userId)
    {
        try
        {
            Optional<User> user = userRepository.findById(userId);
            if(user.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(
                        "User Not Found",
                        HttpStatus.NOT_FOUND.value()
                    )
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                    "User found successfully",
                    HttpStatus.OK.value(),
                    user
                )
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(
                    "Internal Server Error: "+e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            );
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public boolean isUserExist(String email)
    {
        return userRepository.findByEmail(email).isPresent();
    }

    public ResponseEntity<Object> deleteUserById(Integer userId)
    {
        try
        {
            Optional<User> user = userRepository.findById(userId);
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(
                        "User Not Found",
                        HttpStatus.NOT_FOUND.value()
                    )
                );
            }
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse(
                    "User deleted Successfully",
                    HttpStatus.NO_CONTENT.value()
                )
            );
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(
                    "Error while deleting user",
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            );
        }
    }

    public ResponseEntity<Object> getAllUsers()
    {
        try
        {
            List<User> users = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                    "Successfully retrieved all Users",
                    HttpStatus.OK.value(),
                    users
                )
            );
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(
                    "Error occurred while retrieving users",
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            );
        }
    }

    public ResponseEntity<Object> updateUser(Integer userId, User user) {
        try
        {
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(
                                "User Not Found.",
                                HttpStatus.NOT_FOUND.value()
                        )
                );
            }
            User existingUser = optionalUser.get();
            userMapper.mapToUpdatedUser(user,existingUser);
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(existingUser);

            return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("User updated Successfully.",
                    HttpStatus.OK.value(),
                        existingUser
                )
            );
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse("Error occurred while updating user.", HttpStatus.INTERNAL_SERVER_ERROR.value())
            );
        }
    }
}
