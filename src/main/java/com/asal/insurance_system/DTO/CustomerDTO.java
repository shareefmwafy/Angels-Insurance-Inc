package com.asal.insurance_system.DTO;

import com.asal.insurance_system.Enum.EnumRolePermission;

public class CustomerDTO {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String idNumber;
        private EnumRolePermission role;

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

    public EnumRolePermission getRole() {
        return role;
    }

    public void setRole(EnumRolePermission role) {
        this.role = role;
    }
}
