package com.asal.insurance_system.Controller;


import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Service.AuthenticationService;
import com.asal.insurance_system.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    @PostMapping(path = "/add-user")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @GetMapping
    public String get(){
        return "GET:: Admin Controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: Admin Controller";
    }

    @DeleteMapping
    public String Delete(){
        return "DELETE:: Admin Controller";
    }

}
