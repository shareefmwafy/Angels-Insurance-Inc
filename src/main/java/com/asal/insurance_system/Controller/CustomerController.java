package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Mapper.CustomerMapper;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Service.ApiResponse;
import com.asal.insurance_system.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path = "/customer/{customer-id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("customer-id") Integer customerId){
        try{
            Optional<Customer> customer = customerService.getCustomerById(customerId);
            if (customer.isEmpty()) {
                ApiResponse response = new ApiResponse(
                        "Customer with ID "+customerId+" not Found",
                        HttpStatus.NOT_FOUND.value(),
                        null
                );

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(customer.get());
        }
        catch (Exception e){
            ApiResponse response = new ApiResponse(
                    "Error occurred while getting customer:"+e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PostMapping(path = "/add-customer")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerDTO customerDTO)
    {
        try {
            Optional<Customer> existingCustomer = customerService.getCustomerByEmail(customerDTO.getEmail());
            if (existingCustomer.isPresent()){
                ApiResponse response = new ApiResponse(
                        "Customer with email " + customerDTO.getEmail() + " already exists",
                        HttpStatus.CONFLICT.value(),
                        null
                );
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            customerService.addCustomer(customerDTO);

            ApiResponse response = new ApiResponse(
                    "Customer with email " + customerDTO.getEmail() + " has been added successfully",
                    HttpStatus.CREATED.value(),
                    customerDTO
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e){
            ApiResponse response = new ApiResponse(
                    "Error occurred while adding customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }


    @DeleteMapping(path = "/delete-customer/{customer-id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customer-id") Integer customerId){
        try {
            Optional<Customer> customer = customerService.getCustomerById(customerId);
            if (customer.isEmpty()) {
                ApiResponse response = new ApiResponse(
                        "Customer with ID " + customerId + " not found",
                        HttpStatus.NOT_FOUND.value(),
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            customerService.deleteCustomerById(customerId);
            ApiResponse response = new ApiResponse(
                    "Customer with ID " + customerId + " has been deleted successfully",
                    HttpStatus.OK.value(),
                    null
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            ApiResponse response = new ApiResponse(
                    "Error occurred while Deleting customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping(path = "/update-customer/{customer-id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("customer-id") Integer customerId, @RequestBody CustomerDTO customerDTO) {
        try {
            Optional<Customer> existingCustomer = customerService.getCustomerById(customerId);
            Customer customer = existingCustomer.get();

            customer.setFirstName(customerDTO.getFirstName());
            customer.setSecondName(customerDTO.getSecondName());
            customer.setThirdName(customerDTO.getThirdName());
            customer.setLastName(customerDTO.getLastName());
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
            customer.setEmail(customerDTO.getEmail());
            customer.setCountry(customerDTO.getCountry());
            customer.setCity(customerDTO.getCity());
            customer.setStreet(customerDTO.getStreet());
            customer.setIdNumber(customerDTO.getIdNumber());
            customer.setPolicyType(customerDTO.getPolicyType());
            customer.setClaimHistory(customerDTO.getClaimHistory());

            customerService.updateCustomer(customer);


            ApiResponse response = new ApiResponse(
                    "Customer with ID " + customerId + " has been updated successfully",
                    HttpStatus.OK.value(),
                    customer
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        catch (IllegalArgumentException  e) {
            ApiResponse response = new ApiResponse(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
            ApiResponse response = new ApiResponse(
                    "Error occurred while updating customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }





}
