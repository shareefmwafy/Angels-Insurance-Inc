package com.asal.insurance_system.Model;


import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Enum.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer Id;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "second_name")
    protected String secondName;
    @Column(name= "third_name")
    protected String  thirdName;
    @Column(name = "last_name")
    protected String lastName;
    @Column(name = "phone_number")
    protected String phoneNumber;
    @Column(name = "email")
    protected String email;
    @Column(name = "password")
    protected String password;
    @Column(name = "country")
    protected String country;
    @Column(name = "city")
    protected String city;
    @Column(name = "street")
    protected String street;
    @Column(name = "id_number")
    protected String idNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    protected Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "policy_type")
    protected EnumPolicyType PolicyType;
    @Column(name = "claim_history")
    protected String claimHistory;

    public Customer(String firstName, String lastName, String email, String password, String idNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
