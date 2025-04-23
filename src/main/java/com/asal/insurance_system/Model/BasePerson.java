package com.asal.insurance_system.Model;

import com.asal.insurance_system.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BasePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

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
    @Size(min = 8, message = "Password must be at least 8 characters")
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

    @NotNull(message = "Role is mandatory")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    protected Role role;

    public void setId(Integer id) {
        this.id = id;
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

    public void setRole(Role role) {
        this.role = role;
    }
}
