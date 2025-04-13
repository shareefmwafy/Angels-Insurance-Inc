package com.asal.insurance_system.Service;
import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public ResponseEntity<Object> login(AuthenticationRequest authRequest) {
        try{
            Optional<User> user = userRepository.findByEmail(authRequest.getEmail());
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new AuthenticationResponse(
                                "User Not Found",
                                HttpStatus.NOT_FOUND.value()
                        )
                );

            }
            if(authRequest.getEmail().equals(user.get().getEmail()) &&
                    passwordEncoder.matches(authRequest.getPassword(),user.get().getPassword())
            ){
                logger.info("User Found: {}" , user.get().getEmail());

                var jwtToken = jwtService.generateToken(user.get());
                logger.info("JWT Token generated successfully");

                return ResponseEntity.status(HttpStatus.OK).body(
                        new AuthenticationResponse(
                                "User Found Successfully",
                                user.get().getId(),
                                HttpStatus.OK.value(),
                                jwtToken
                        )
                );
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new AuthenticationResponse(
                            "Invalid Email or password",
                            HttpStatus.NOT_FOUND.value()
                    )
            );

        }

        catch (Exception e){
            logger.error("An error occurred during login: {}",e.getMessage());
            String message = "An error occurred during login: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AuthenticationResponse(
                            "User Found Successfully",
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                            )
            );
        }
    }
}












