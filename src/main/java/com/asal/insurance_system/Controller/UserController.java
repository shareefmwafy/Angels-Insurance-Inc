package com.asal.insurance_system.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public String post(){
        return "POST:: User Controller";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public String get(){
        return "GET:: User Controller";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping
    public String put(){
        return "PUT:: User Controller";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping
    public String Delete(){
        return "DELETE:: User Controller";
    }
}
