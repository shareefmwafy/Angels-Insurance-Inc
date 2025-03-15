package com.asal.insurance_system.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @PostMapping
    public String post(){
        return "POST:: User Controller";
    }

    @GetMapping
    public String get(){
        return "GET:: User Controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: User Controller";
    }

    @DeleteMapping
    public String Delete(){
        return "DELETE:: User Controller";
    }
}
