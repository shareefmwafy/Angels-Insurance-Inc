package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping(path = "/add-customer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO)
    {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.ok("Customer Added Successfully");
    }

}
