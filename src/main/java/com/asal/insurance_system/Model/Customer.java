package com.asal.insurance_system.Model;


import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer implements UserDetails{
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer Id;
    @Getter
    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    @Column(name = "first_name")
    protected String firstName;
    @Getter
    @NotNull(message = "Second name is required")
    @Size(min = 1, max = 50, message = "Second name must be between 1 and 50 characters")
    @Column(name = "second_name")
    protected String secondName;
    @Getter
    @NotNull(message = "Third name is required")
    @Size(min = 1, max = 50, message = "Third name must be between 1 and 50 characters")
    @Column(name= "third_name")
    protected String  thirdName;
    @Getter
    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    @Column(name = "last_name")
    protected String lastName;
    @Getter
    @NotNull(message = "Phone number is required")
    @Column(name = "phone_number")
    protected String phoneNumber;
    @Getter
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    protected String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be between 6 and 20 characters")
    @Column(name = "password")
    protected String password;
    @NotNull(message = "Country is required")
    @Size(min = 1, max = 50, message = "Country must be between 1 and 50 characters")
    @Column(name = "country")
    protected String country;

    @NotNull(message = "City is required")
    @Size(min = 1, max = 50, message = "City must be between 1 and 50 characters")
    @Column(name = "city")
    protected String city;
    @NotNull(message = "Street is required")
    @Size(min = 1, max = 100, message = "Street must be between 1 and 100 characters")
    @Column(name = "street")
    protected String street;
    @NotNull(message = "ID number is required")
    @Column(name = "id_number")
    protected String idNumber;
    @NotNull(message = "Policy type is required")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "policy_type")
    protected EnumPolicyType PolicyType;
    @Column(name = "claim_history")
    protected String claimHistory;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @OneToMany(mappedBy = "customer")
    private List<Policy> policies;

    @OneToMany(mappedBy = "customer")
    private List<Accident> accidents;




    public Customer(String firstName, String secondName, String thirdName, String lastName, String phoneNumber, String email, String password, String country, String city, String street, String idNumber, EnumPolicyType policyType, String claimHistory, Role role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.street = street;
        this.idNumber = idNumber;
        PolicyType = policyType;
        this.claimHistory = claimHistory;
        this.role = role;
    }

    public Customer (){}

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;
        return Objects.equals(Id, customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }



    public void setPolicyType(EnumPolicyType policyType) {
        PolicyType = policyType;
    }


    public void setClaimHistory(String claimHistory) {
        this.claimHistory = claimHistory;
    }


    public void setRole(Role role) {
        this.role = role;
    }
}
