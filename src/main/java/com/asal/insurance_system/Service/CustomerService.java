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

    private final CustomerMapper customerMapper;

    private final Logger logeer = LoggerFactory.getLogger(CustomerService.class);
    public void addCustomer(CustomerDTO customerDTO) {
        try{
            Customer customer = customerMapper.mapToEntity(customerDTO);
            customerRepository.save(customer);
        }
        catch (Exception e){
            logeer.error("Error Occurred while Adding Customer");
            throw e;
        }
    }

    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }
    public Optional<Customer> getCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
