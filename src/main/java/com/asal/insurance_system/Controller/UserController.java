package com.asal.insurance_system.Controller;


import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.ApiResponse;
import com.asal.insurance_system.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/add-user")
    public ResponseEntity<Object> register(@RequestBody @Valid UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Object> get(@PathVariable("userId") Integer userId)
    {
        return userService.getUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public String put(){
        return "PUT:: User Controller";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public String Delete(){
        return "DELETE:: User Controller";
    }
}
