package com.asal.insurance_system.DTO;

import com.asal.insurance_system.Enum.Role;
import jakarta.validation.constraints.*;

import java.util.Date;

public class UserDTO {

    @NotNull(message = "First name is mandatory")
    private String firstName;
    @NotNull(message = "Last name is mandatory")
    private String lastName;
    @NotNull(message = "Email is mandatory")
    @Email(message = "Write a Correct Email")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotNull(message = "Id Number is mandatory")
    private String idNumber;
    @NotNull(message = "Role is mandatory")
    private Role role;
    private Integer departmentId;
    @NotNull(message = "Hiring date is mandatory")
    @PastOrPresent(message = "Hiring date cannot be in the future")
    private Date hiringDate;
    @Positive(message = "Salary must be a positive number")
    private Float salary;

    @NotNull(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotNull(message = "Third name is mandatory")
    private String thirdName;
    @NotNull(message = "Third name is mandatory")
    private String secondName;

    @NotNull(message = "Street is mandatory")
    private String street;

    @NotNull(message = "City is mandatory")
    private String city;

    @NotNull(message = "Country is mandatory")
    private String country;

    @NotNull(message = "Second name is mandatory")




    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String email, String password, String idNumber, Role role, Integer departmentId, Date hiringDate, Float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
        this.role = role;
        this.departmentId = departmentId;
        this.hiringDate = hiringDate;
        this.salary = salary;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", role=" + role +
                ", departmentId=" + departmentId +
                ", hiringDate=" + hiringDate +
                ", salary=" + salary +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
