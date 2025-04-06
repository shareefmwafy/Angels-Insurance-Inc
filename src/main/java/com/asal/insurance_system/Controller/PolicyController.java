package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.Request.PolicyRequestDTO;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.asal.insurance_system.Exception.ErrorResponseUtil;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService){
        this.policyService = policyService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<?> createPolicy(@Valid @RequestBody PolicyRequestDTO dto){
        try {
            Policy newPolicy = policyService.createPolicy(dto);
            return new ResponseEntity<>(newPolicy, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {

            return new ResponseEntity<>(ErrorResponseUtil.createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {

            return new ResponseEntity<>(ErrorResponseUtil.createErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
