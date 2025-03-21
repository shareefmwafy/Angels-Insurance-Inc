package com.asal.insurance_system.Auth;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthenticationResponse {

    private String message;
    private int statusCode;
    private String token;

    public AuthenticationResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public AuthenticationResponse(String message, int statusCode, String token) {
        this.message = message;
        this.statusCode = statusCode;
        this.token = token;
    }
}
