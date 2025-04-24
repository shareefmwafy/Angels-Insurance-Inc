package com.asal.insurance_system.Service;


import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.DTO.Request.CustomerRequest;
import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.DTO.Response.CustomerResponse;
import com.asal.insurance_system.Enum.Role;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Mapper.CustomerMapper;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;

    private final JwtService jwtService;


    @Autowired
    private AuditLogService logService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerResponse updateCustomer(Integer customerId, CustomerRequest customerRequest, User userDetails) {
        Customer customerInDb = customerRepository.findById(customerId)
                .orElseThrow(()-> new UsernameNotFoundException("Customer Not Found"));

        customerRequest.setPassword(passwordEncoder.encode(customerRequest.getPassword()));

        customerMapper.mapToUpdateCustomerRequest(customerInDb,customerRequest);

        customerRepository.save(customerInDb);
        CustomerResponse customerResponse = customerMapper.mapToCustomerResponse(customerInDb);

        logService.logAction(
                "Update Customer Information",
                "Customers",
                customerInDb.getId(),
                " ",
                " ",
                userDetails.getId(),
                userDetails.getRole().name()
        );

        return customerResponse;


    }

    public boolean deleteCustomerById(Integer customerId,User userDetails) {
        if (!customerRepository.existsById(customerId)) {
            return false;
        }
        customerRepository.deleteById(customerId);
        logService.logAction(
                "Delete Customer",
                "Customers",
                customerId,
                "",
                "",
                userDetails.getId(),
                userDetails.getRole().name()
        );
        return true;
    }


    public CustomerResponse addCustomer(CustomerRequest customerRequest, User userDetails) {

            Customer customer = customerRepository.findByEmail(customerRequest.getEmail());
            if(customer != null){
                throw new IllegalArgumentException("Customer Already Exists with email: "+ customerRequest.getEmail());
            }
            Customer newCustomer = customerMapper.mapToEntity(customerRequest);

            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            newCustomer.setRole(Role.CUSTOMER);
            customerRepository.save(newCustomer);
            logService.logAction(
                    "Create New Customer",
                    "Customers",
                    newCustomer.getId(),
                    "",
                    "",
                    userDetails.getId(),
                    userDetails.getRole().name()
            );
            return customerMapper.mapToCustomerResponse(newCustomer);

    }


    public CustomerResponse getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));

        return customerMapper.mapToCustomerResponse(customer);
    }


    public List<CustomerResponse> getAllCustomers() {
             return customerRepository.findAll().stream()
                     .map(customerMapper::mapToCustomerResponse)
                     .collect(Collectors.toList());
    }

    public AuthenticationResponse customerLogin(AuthenticationRequest request) {
        try {
            Customer customer = customerRepository.findByEmail(request.getEmail());

            if (Objects.isNull(customer)) {
                return new AuthenticationResponse(
                        "Customer Not Found",
                        HttpStatus.NOT_FOUND.value()
                );
            }

            if (request.getEmail().equals(customer.getEmail()) &&
                    passwordEncoder.matches(request.getPassword(), customer.getPassword())) {

                logger.info("Customer Found: {}", customer.getEmail());

                String jwtToken = jwtService.generateTokenForCustomer(customer);
                logger.info("JWT Token generated successfully for customer");

                logService.logAction(
                        "Customer Login",
                        "Customers",
                        customer.getId(),
                        "", "",
                        customer.getId(),
                        customer.getRole().name()
                );

                return new AuthenticationResponse(
                        "Customer Logged in Successfully",
                        customer.getId(),
                        HttpStatus.OK.value(),
                        jwtToken
                );
            }

            return new AuthenticationResponse(
                    "Invalid email or password",
                    HttpStatus.UNAUTHORIZED.value()
            );

        } catch (Exception e) {
            logger.error("An error occurred during customer login: {}", e.getMessage());
            return new AuthenticationResponse(
                    "An error occurred during login: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
        }
    }
}
