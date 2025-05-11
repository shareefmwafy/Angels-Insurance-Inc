package com.asal.insurance_system.Controller;

import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.Request.CustomerRequest;
import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.DTO.Response.CustomerResponse;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers(){
        List<CustomerResponse> customers = customerService.getAllCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(
            new ApiResponse<>(
            "All Customers",
                HttpStatus.OK.value(),
                customers
            )
        );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping(path = "/{customer-id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable("customer-id") Integer customerId){
       CustomerResponse customerResponse =customerService.getCustomerById(customerId);
       return ResponseEntity.status(HttpStatus.OK).body(
           new ApiResponse<>(
               "Customer Found Successfully",
               HttpStatus.OK.value(),
               customerResponse
           )
       );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<ApiResponse<CustomerResponse>> addCustomer(
            @Valid @RequestBody CustomerRequest customerRequest,
            @AuthenticationPrincipal User userDetails
            )
    {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest,userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ApiResponse<>(
                "Customer Created Successfully",
                HttpStatus.CREATED.value(),
                customerResponse
            )
        );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping(path = "/{customer-id}")
    public ResponseEntity<ApiResponse> deleteCustomer(
            @PathVariable("customer-id") Integer customerId,
            @AuthenticationPrincipal User userDetails

    ){
        boolean result =  customerService.deleteCustomerById(customerId,userDetails);
        if (result){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                    "User Deleted Successfully",
                    HttpStatus.NO_CONTENT.value()
                )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ApiResponse<>(
                "User Not Found",
                HttpStatus.NOT_FOUND.value()
            )
        );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping(path = "/{customer-id}")
    public ResponseEntity<ApiResponse> updateCustomer(
            @PathVariable("customer-id") Integer customerId,
            @Valid @RequestBody CustomerRequest customerRequest,
            @AuthenticationPrincipal User userDetails
    ) {
        CustomerResponse customer = customerService.updateCustomer(customerId, customerRequest, userDetails);

        if(Objects.isNull(customer)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                    "Customer Not Found",
                    HttpStatus.NOT_FOUND.value()
                )
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
            new ApiResponse(
                "Customer Updated Successfully",
                HttpStatus.OK.value(),
                customer
            )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> customerLogin(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = customerService.customerLogin(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
