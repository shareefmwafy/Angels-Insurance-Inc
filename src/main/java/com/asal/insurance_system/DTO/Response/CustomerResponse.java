package com.asal.insurance_system.DTO.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private Integer id;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String country;
    private String city;
    private String street;
    private String idNumber;
    private String claimHistory;
    private String role;
    private String policyType;
    private String username;
}
