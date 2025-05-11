package com.asal.insurance_system.Auth;

import com.asal.insurance_system.Enum.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class AuthenticationResponse {

    private String message;
    private int status;
    private String token;
    private Integer id;
    private Role role;

    public AuthenticationResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public AuthenticationResponse(String message, Integer id, int status, String token, Role role) {
        this.message = message;
        this.status = status;
        this.token = token;
        this.id = id;
        this.role = role;
    }
}
