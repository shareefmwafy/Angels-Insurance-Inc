package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.Request.PolicyRequestDTO;
import com.asal.insurance_system.DTO.Response.PolicyResponseDTO;
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

import java.util.List;

import static com.asal.insurance_system.Exception.ErrorResponseUtil.createErrorResponse;

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

            return new ResponseEntity<>(createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {

            return new ResponseEntity<>(createErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<?> getAllPolices(){
        try{
            List<PolicyResponseDTO> policies = policyService.getAllPolices();
            if (policies.isEmpty()){
                return new ResponseEntity<>(createErrorResponse("No Policies Found", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(policies, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(createErrorResponse("Error while fetching policies: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getPolicyById(@PathVariable Integer id) {
        try {
            PolicyResponseDTO policy = policyService.getPolicyById(id);
            return new ResponseEntity<>(policy, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(createErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<?> updatePolicy(@PathVariable Integer id, @RequestBody PolicyRequestDTO requestDTO) {
        try {
            Policy policy = policyService.updatePolicy(id, requestDTO);
            return new ResponseEntity<>(policy, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(createErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePolicy(@PathVariable Integer id) {
        try {
            boolean policy = policyService.deletePolicy(id);
            return new ResponseEntity<>(policy, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(createErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }









}
