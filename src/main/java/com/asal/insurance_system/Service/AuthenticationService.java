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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditLogService logService;
    private final JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(authRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return new AuthenticationResponse("User Not Found", 404);
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return new AuthenticationResponse("Invalid Email or password", 404);
        }

        String jwtToken = jwtService.generateToken(user);

        logService.logAction(
                "User Login",
                " ",
                null,
                " ",
                " ",
                user.getId(),
                user.getRole().toString()
        );

        return new AuthenticationResponse(
                "User Found Successfully",
                user.getId(),
                200,
                jwtToken,
                user.getRole()
        );
    }

}












