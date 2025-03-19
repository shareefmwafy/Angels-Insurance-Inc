package com.asal.insurance_system.Service;

import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Enum.Role;
import com.asal.insurance_system.Mapper.UserMapper;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final JwtService jwtService;

    public AuthenticationResponse addUser(UserDTO userDTO) {
        try{
            User user = userMapper.mapToEntity(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            logger.info("JWT Token generated successfully");
            String successMessage = "Successfully Created new user with Role: "+ user.getRole();

            logger.info(successMessage);

            return AuthenticationResponse.builder()
                    .message(successMessage)
                    .token(jwtToken)
                    .build();
        }
        catch (Exception e){
            logger.error("User Doesn't Added: {}",e.getMessage());
            String faildMessage = "Error occurred while adding user";
            return AuthenticationResponse.builder()
                    .message(faildMessage)
                    .build();
        }
    }
}
