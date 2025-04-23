package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Policy;

import java.util.List;

public class CustomerWithPoliciesResponse {
    private Customer customer;
    private List<Policy> policies;

    public CustomerWithPoliciesResponse(Customer customer) {
        this.customer = customer;
//        this.policies = policies;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }
}
