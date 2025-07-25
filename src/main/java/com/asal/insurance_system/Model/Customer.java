package com.asal.insurance_system.Model;
import com.asal.insurance_system.Enum.EnumPolicyType;
import com.asal.insurance_system.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "customers")
public class Customer extends BasePerson implements UserDetails{

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

    @Override
    public Integer getId() {
        return super.getId();
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;
        return Objects.equals(id, customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public void setClaimHistory(String claimHistory) {
        this.claimHistory = claimHistory;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
