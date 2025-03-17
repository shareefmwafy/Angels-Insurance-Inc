package com.asal.insurance_system.Service;

import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Enum.Role;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO userDTO) {
        try{
            User user = new User(
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getEmail(),
                    this.passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getIdNumber(),
                    userDTO.getRole(),
                    userDTO.getDepartmentId(),
                    userDTO.getHiringDate(),
                    userDTO.getSalary()
            );
            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            logger.info("JWT Token generated successfully");

            if(user.getRole() == Role.ADMIN)
                logger.info("Successfully created new Admin with email: {}", user.getEmail());
            else{
                logger.info("Successfully created new Employee User with email: {}", user.getEmail());
            }

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        catch (Exception e){
            logger.error("User Doesn't Added: {}",e.getMessage());
            throw e;
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        try {
            logger.info("Attempting authentication for: " + authRequest.getEmail());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
            var user = userRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

            logger.info("User Found: {}" , user.getEmail());

            var jwtToken = jwtService.generateToken(user);
            logger.info("JWT Token generated successfully");

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        catch (UsernameNotFoundException e){
            logger.warn("User not Found {}",authRequest.getEmail());
            throw e;
        }
        catch (Exception e){
            logger.error("Authentication Failed: {}",e.getMessage());
            throw e;
        }
    }
}












