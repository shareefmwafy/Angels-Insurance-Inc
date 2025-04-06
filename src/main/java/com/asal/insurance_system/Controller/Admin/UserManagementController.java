package com.asal.insurance_system.Controller.Admin;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class UserManagementController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDTO userDTO)
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
    @PutMapping("/user/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") Integer userId,@Valid @RequestBody User user){
        return userService.updateUser(userId, user);
    }
}
