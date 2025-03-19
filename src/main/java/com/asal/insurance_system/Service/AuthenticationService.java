package com.asal.insurance_system.Service;
import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
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


    public AuthenticationResponse login(AuthenticationRequest authRequest) {
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

            if(!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())
                || !(authRequest.getEmail().equals(user.getEmail()))
            ){
                String message = "Invalid credentials. Please check your Email or password.";
                return AuthenticationResponse.builder()
                        .message(message)
                        .build();

            }

            logger.info("User Found: {}" , user.getEmail());

            var jwtToken = jwtService.generateToken(user);
            logger.info("JWT Token generated successfully");
            String message = "Login Successful for User: "+ user.getEmail();
            return AuthenticationResponse.builder()
                    .message(message)
                    .token(jwtToken)
                    .build();
        }
        catch (UsernameNotFoundException e){
            logger.warn("User not Found {}",authRequest.getEmail());
            throw e;
        }
        catch (Exception e){
            logger.error("An error occurred during login: {}",e.getMessage());
            String message = "An error occurred during login: " + e.getMessage();
            return AuthenticationResponse.builder()
                    .message(message)
                    .build();
        }
    }
}












