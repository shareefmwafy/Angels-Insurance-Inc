package com.asal.insurance_system.Controller.Admin;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAdmin(){
        return "GET:: Admin Controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String updateAdmin(){
        return "PUT:: Admin Controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String deleteAdmin(){
        return "DELETE:: Admin Controller";
    }

}
