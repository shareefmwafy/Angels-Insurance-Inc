package com.asal.insurance_system.Model;


import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer Id;
    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    @Column(name = "first_name")
    protected String firstName;
    @NotNull(message = "Second name is required")
    @Size(min = 1, max = 50, message = "Second name must be between 1 and 50 characters")
    @Column(name = "second_name")
    protected String secondName;
    @NotNull(message = "Third name is required")
    @Size(min = 1, max = 50, message = "Third name must be between 1 and 50 characters")
    @Column(name= "third_name")
    protected String  thirdName;
    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    @Column(name = "last_name")
    protected String lastName;
    @NotNull(message = "Phone number is required")
    @Column(name = "phone_number")
    protected String phoneNumber;
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

    public Customer(String firstName, String secondName, String thirdName, String lastName, String phoneNumber, String email, String password, String country, String city, String street, String idNumber, EnumPolicyType policyType, String claimHistory) {
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
    }

    public Customer (){}

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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    public EnumPolicyType getPolicyType() {
        return PolicyType;
    }

    public void setPolicyType(EnumPolicyType policyType) {
        PolicyType = policyType;
    }

    public String getClaimHistory() {
        return claimHistory;
    }

    public void setClaimHistory(String claimHistory) {
        this.claimHistory = claimHistory;
    }
}
