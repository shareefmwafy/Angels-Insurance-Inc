package com.asal.insurance_system.Auth;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthenticationResponse {

    private String message;
    private int status;
    private String token;

    public AuthenticationResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public AuthenticationResponse(String message, int status, String token) {
        this.message = message;
        this.status = status;
        this.token = token;
    }
}
