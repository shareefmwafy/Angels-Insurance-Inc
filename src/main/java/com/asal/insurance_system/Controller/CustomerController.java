package com.asal.insurance_system.Controller;

import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<Object> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping(path = "/customer/{customer-id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("customer-id") Integer customerId){
        return customerService.getCustomerById(customerId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerDTO customerDTO)
    {
        return customerService.addCustomer(customerDTO);
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping(path = "/customer/{customer-id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customer-id") Integer customerId){
        return customerService.deleteCustomerById(customerId);
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping(path = "/customer/{customer-id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("customer-id") Integer customerId,@Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> customerLogin(@Valid @RequestBody AuthenticationRequest request){
        return  customerService.customerLogin(request);
     }






}
