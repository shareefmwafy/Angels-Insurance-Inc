package com.asal.insurance_system.DTO.Request;

import com.asal.insurance_system.Enum.EnumPolicyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    @Getter
    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    protected String firstName;

    @Getter
    @NotNull(message = "Second name is required")
    @Size(min = 1, max = 50, message = "Second name must be between 1 and 50 characters")
    protected String secondName;

    @Getter
    @NotNull(message = "Third name is required")
    @Size(min = 1, max = 50, message = "Third name must be between 1 and 50 characters")
    protected String  thirdName;

    @Getter
    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    protected String lastName;

    @Getter
    @NotNull(message = "Phone number is required")
    protected String phoneNumber;

    @Getter
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    protected String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be between 6 and 20 characters")
    protected String password;

    @NotNull(message = "Country is required")
    protected String country;

    @NotNull(message = "City is required")
    protected String city;

    @NotNull(message = "Street is required")
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

}
