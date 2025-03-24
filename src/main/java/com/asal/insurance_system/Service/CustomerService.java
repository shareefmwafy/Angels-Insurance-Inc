package com.asal.insurance_system.Service;


import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Mapper.CustomerMapper;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;


    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public ResponseEntity<Object> updateCustomer(Integer customerId, Customer customer) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if(optionalCustomer.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(
                                "Customer Not Found",
                                HttpStatus.NOT_FOUND.value()
                        )
                );
            }
            Customer existingCustomer = optionalCustomer.get();
            customerMapper.mapToUpdatedCustomer(customer,existingCustomer);
            existingCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(existingCustomer);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Customer Updated Successfully",
                            HttpStatus.OK.value(),
                            existingCustomer
                    )
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(
                            "Error While update Customer: "+e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }

    public ResponseEntity<Object> deleteCustomerById(Integer customerId) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if(customer.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(
                                "Customer Not Found",
                                HttpStatus.NOT_FOUND.value()
                        )
                );
            }
            customerRepository.deleteById(customerId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ApiResponse(
                            "Customer Deleted Successfully",
                            HttpStatus.NO_CONTENT.value()
                    )
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(
                            "Error While Deleting Customer: "+e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }

    public ResponseEntity<Object> addCustomer(CustomerDTO customerDTO) {
        try {
            Optional<Customer> customer = customerRepository.findByEmail(customerDTO.getEmail());
            if(customer.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ApiResponse(
                                "This Customer Already Exist",
                                HttpStatus.CONFLICT.value()
                        )
                );
            }
            Customer newCustomer = customerMapper.mapToEntity(customerDTO);
            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            customerRepository.save(newCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse(
                            "Customer Created Successfully",
                            HttpStatus.CREATED.value()
                    )
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(
                            "Error While Adding Customer: "+e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }


    public ResponseEntity<Object> getCustomerById(Integer customerId) {
       try {
           Optional<Customer> customer = customerRepository.findById(customerId);
           if(customer.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                       new ApiResponse(
                               "Customer Not Found",
                               HttpStatus.NOT_FOUND.value()
                       )
               );
           }

           return ResponseEntity.status(HttpStatus.OK).body(
                   new ApiResponse(
                           "Successfully found Customer",
                           HttpStatus.OK.value(),
                           customer
                   )
           );
       }
       catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                   new ApiResponse(
                           "Error While getting Customer: "+e.getMessage(),
                           HttpStatus.INTERNAL_SERVER_ERROR.value()
                   )
           );
       }
    }

    public ResponseEntity<Object> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Successfully get all Customers",
                            HttpStatus.OK.value(),
                            customers
                    )
            );
        }
        catch (Exception e){
            logger.error("Error While getting customers");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(
                            "Error While getting customers: "+e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }
}
