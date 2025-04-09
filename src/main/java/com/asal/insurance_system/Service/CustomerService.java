package com.asal.insurance_system.Service;


import com.asal.insurance_system.Auth.AuthenticationRequest;
import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.DTO.Request.ClaimRequest;
import com.asal.insurance_system.DTO.Response.ClaimResponse;
import com.asal.insurance_system.Enum.Role;
import com.asal.insurance_system.Mapper.ClaimMapper;
import com.asal.insurance_system.Mapper.CustomerMapper;
import com.asal.insurance_system.Model.Claim;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Repository.ClaimRepository;
import com.asal.insurance_system.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.asal.insurance_system.Enum.EnumClaimStatus.PENDING;

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

    private final ClaimRepository claimRepository;
    private final ClaimMapper claimMapper;


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
            Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
            if(customer != null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ApiResponse(
                                "This Customer Already Exist",
                                HttpStatus.CONFLICT.value()
                        )
                );
            }
            Customer newCustomer = customerMapper.mapToEntity(customerDTO);
            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            newCustomer.setRole(Role.CUSTOMER);
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
            Optional<Customer> customerOptional = customerRepository.findById(customerId);

            if (customerOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(
                                "Customer Not Found",
                                HttpStatus.NOT_FOUND.value()
                        )
                );
            }

            Customer customer = customerOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Successfully found Customer with Policies",
                            HttpStatus.OK.value(),
                            customer
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(
                            "Error While getting Customer: " + e.getMessage(),
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

    public ResponseEntity<Object> customerLogin(AuthenticationRequest request) {
        try {
            Customer customer = customerRepository.findByEmail(request.getEmail());

            if (Objects.isNull(customer)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new AuthenticationResponse(
                                "Customer Not Found",
                                HttpStatus.NOT_FOUND.value()
                        )
                );
            }

            if (request.getEmail().equals(customer.getEmail()) &&
                    passwordEncoder.matches(request.getPassword(), customer.getPassword())) {

                logger.info("Customer Found: {}", customer.getEmail());

                String jwtToken = jwtService.generateTokenForCustomer(customer);
                logger.info("JWT Token generated successfully for customer");

                return ResponseEntity.status(HttpStatus.OK).body(
                        new AuthenticationResponse(
                                "Customer Logged in Successfully",
                                HttpStatus.OK.value(),
                                jwtToken
                        )
                );
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthenticationResponse(
                            "Invalid email or password",
                            HttpStatus.UNAUTHORIZED.value()
                    )
            );

        } catch (Exception e) {
            logger.error("An error occurred during customer login: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AuthenticationResponse(
                            "An error occurred during login: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }

    public ClaimResponse createNewClaim(Integer customerId, ClaimRequest claimRequest, Integer customerLoggedInId) {
        Claim claim = claimMapper.mapToEntity(claimRequest);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new UsernameNotFoundException("Customer Not Found"));
        if (customerId == customerLoggedInId) {
            System.out.println("Claim Request: "+ claimRequest);
            claim.setStatus(PENDING);

            claim.setCustomer(customer);
            claimRepository.save(claim);
            return claimMapper.mapToResponseDto(claim);
        }
        return null;
    }
}
