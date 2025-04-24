package com.asal.insurance_system.Controller;


import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> auth(@Valid @RequestBody AuthenticationRequest authRequest) {
        try {
            AuthenticationResponse response = authenticationService.login(authRequest);

            if (response.getStatus() == 200) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AuthenticationResponse("Server error: " + e.getMessage(), 500)
            );
        }
    }



}
