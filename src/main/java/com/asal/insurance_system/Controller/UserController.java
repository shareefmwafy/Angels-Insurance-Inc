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
    public ResponseEntity<Object> get(@PathVariable("userId") Integer userId){
        try
        {
            Optional<User> user = userService.findUserById(userId);
            if(user.isEmpty()){
                return ResponseEntity.ok(
                        new ApiResponse("user Not Found", HttpStatus.NOT_FOUND.value())
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse("User Found", HttpStatus.OK.value(),user)
            );
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Internal Server Error: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value())
            );
        }
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
