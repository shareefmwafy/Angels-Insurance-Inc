package com.asal.insurance_system.Controller;

import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.DTO.Request.ClaimRequest;
import com.asal.insurance_system.DTO.Response.ClaimResponse;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.Claim;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Service.ApiResponse;
import com.asal.insurance_system.Service.CancellationRequestService;
import com.asal.insurance_system.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    private final CancellationRequestService cancellationRequestService;

    public CustomerController(CancellationRequestService cancellationRequestService) {
        this.cancellationRequestService = cancellationRequestService;
    }


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


    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/request-cancellation/{policyId}")
    public ResponseEntity<ApiResponse> requestPolicyCancellation(@PathVariable Integer policyId, @RequestBody String reason, @AuthenticationPrincipal Customer userDetails) {
        try {
            Integer customerId = userDetails.getId();
            boolean result = cancellationRequestService.requestPolicyCancellation(policyId, reason, customerId);
            if (!result)
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("you Can't Create this Request now", HttpStatus.CONFLICT.value()));

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Cancellation request sent successfully", HttpStatus.CREATED.value()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while requesting cancellation", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/claim/{customerId}")
    public ResponseEntity<ClaimResponse> requestClaim(@PathVariable Integer customerId,@RequestBody ClaimRequest ClaimRequest, @AuthenticationPrincipal Customer customerDetails){
        Integer customerLoggedInId = customerDetails.getId();
        ClaimResponse claimResponse =customerService.createNewClaim(customerId,ClaimRequest,customerLoggedInId);

        if (claimResponse != null){
            return ResponseEntity.status(HttpStatus.OK).body(claimResponse);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
