package com.asal.insurance_system.DTO;

import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String firstName;

    @NotNull(message = "Second name is required")
    @Size(min = 1, max = 50, message = "Second name must be between 1 and 50 characters")
    private String secondName;

    @NotNull(message = "Third name is required")
    @Size(min = 1, max = 50, message = "Third name must be between 1 and 50 characters")
    private String thirdName;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotNull(message = "Country is required")
    @Size(min = 1, max = 50, message = "Country must be between 1 and 50 characters")
    private String country;

    @NotNull(message = "City is required")
    @Size(min = 1, max = 50, message = "City must be between 1 and 50 characters")
    private String city;

    @NotNull(message = "Street is required")
    @Size(min = 1, max = 100, message = "Street must be between 1 and 100 characters")
    private String street;

    @NotNull(message = "ID number is required")
    private String idNumber;

    @NotNull(message = "Policy type is required")
    private EnumPolicyType policyType;
    private String claimHistory;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public EnumPolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(EnumPolicyType policyType) {
        this.policyType = policyType;
    }

    public String getClaimHistory() {
        return claimHistory;
    }

    public void setClaimHistory(String claimHistory) {
        this.claimHistory = claimHistory;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


}
