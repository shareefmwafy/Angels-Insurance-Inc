package com.asal.insurance_system.Service;


import com.asal.insurance_system.DTO.CustomerDTO;
import com.asal.insurance_system.Enum.EnumRolePermission;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public void addCustomer(CustomerDTO customerDTO) {
        customerDTO.setRole(EnumRolePermission.CUSTOMER);
        Customer customer = new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                this.passwordEncoder.encode(customerDTO.getPassword()),
                customerDTO.getIdNumber(),
                customerDTO.getRole()
        );
        customerRepository.save(customer);
    }
}
