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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody @Valid UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") Integer userId)
    {
        return userService.getUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer userId)
    {
        return userService.deleteUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/user/{userId}")
    public String updateUser(@PathVariable("userId") Integer userId){
        return "PUT:: User Controller";
    }
}
