package com.asal.insurance_system.Controller;

import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.Request.CustomerRequest;
import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.DTO.Response.CustomerResponse;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.CancellationRequestService;
import com.asal.insurance_system.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CustomerService customerService;
    private final CancellationRequestService cancellationRequestService;

    public CustomerController(CancellationRequestService cancellationRequestService) {
        this.cancellationRequestService = cancellationRequestService;
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers(){
        List<CustomerResponse> customers = customerService.getAllCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(
            new ApiResponse<List<CustomerResponse>>(
            "All Customers",
                HttpStatus.OK.value(),
                customers
            )
        );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping(path = "/customer/{customer-id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable("customer-id") Integer customerId){
       try {
           CustomerResponse customerResponse =customerService.getCustomerById(customerId);
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ApiResponse<>(
                           "Customer Found Successfully",
                           HttpStatus.OK.value(),
                           customerResponse
                   )
           );
       }
       catch (ResourceNotFoundException ex){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   new ApiResponse<>(
                           ex.getMessage(),
                           HttpStatus.NOT_FOUND.value()
                   )
           );
       }
       catch (Exception ex){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                   new ApiResponse<>(
                           "Error While Adding Customer",
                           HttpStatus.INTERNAL_SERVER_ERROR.value()
                   )
           );
       }



    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<ApiResponse<CustomerResponse>> addCustomer(
            @Valid @RequestBody CustomerRequest customerRequest,
            @AuthenticationPrincipal User userDetails
            )

    {
        try {
            CustomerResponse customerResponse = customerService.addCustomer(customerRequest,userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(
                            "Customer Created Successfully",
                            HttpStatus.CREATED.value(),
                            customerResponse
                    )
            );
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponse<>(
                            ex.getMessage(),
                            HttpStatus.CONFLICT.value()
                    )
            );
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(
                            "Error While Adding Customer",
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping(path = "/customer/{customer-id}")
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
    @PutMapping(path = "/customer/{customer-id}")
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



    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/request-cancellation/{policyId}")
    public ResponseEntity<ApiResponse> requestPolicyCancellation(@PathVariable Integer policyId, @RequestBody String reason) {
        try {
            boolean result = cancellationRequestService.requestPolicyCancellation(policyId, reason);
            if (!result)
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("You have made this request before.", HttpStatus.CONFLICT.value()));

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Cancellation request sent successfully", HttpStatus.CREATED.value()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while requesting cancellation", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> customerLogin(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = customerService.customerLogin(request);

        return ResponseEntity.status(response.getStatus()).body(response);
    }






}
