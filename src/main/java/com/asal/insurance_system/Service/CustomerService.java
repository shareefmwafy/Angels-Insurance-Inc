package com.asal.insurance_system.Service;


import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getFirstName(),
                customerDTO.getSecondName(),
                customerDTO.getThirdName(),
                customerDTO.getLastName(),
                customerDTO.getPhoneNumber(),
                customerDTO.getEmail(),
                this.passwordEncoder.encode(customerDTO.getPassword()),
                customerDTO.getCountry(),
                customerDTO.getCity(),
                customerDTO.getStreet(),
                customerDTO.getIdNumber(),
                customerDTO.getPolicyType(),
                customerDTO.getClaimHistory()
        );
        customerRepository.save(customer);
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
