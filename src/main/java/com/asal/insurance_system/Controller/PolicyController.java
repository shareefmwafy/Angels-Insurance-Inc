package com.asal.insurance_system.Controller;
import com.asal.insurance_system.DTO.Request.PolicyRequestDTO;
import com.asal.insurance_system.DTO.Response.PolicyResponseDTO;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.PolicyDocumentService;
import com.asal.insurance_system.Service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

import static com.asal.insurance_system.Exception.ErrorResponseUtil.createErrorResponse;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    private final PolicyService policyService;
    private final PolicyDocumentService policyDocumentService;

    public PolicyController(PolicyService policyService, PolicyDocumentService policyDocumentService){
        this.policyService = policyService;
        this.policyDocumentService = policyDocumentService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<?> createPolicy(@Valid @RequestBody PolicyRequestDTO dto, @AuthenticationPrincipal User userDetails){
        Policy newPolicy = policyService.createPolicy(dto, userDetails);
        return new ResponseEntity<>(newPolicy, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<?> getAllPolices(){
        List<PolicyResponseDTO> policies = policyService.getAllPolices();
        if (policies.isEmpty()){
            return new ResponseEntity<>(createErrorResponse("No Policies Found", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('CUSTOMER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getPolicyById(@PathVariable Integer id) {
        PolicyResponseDTO policy = policyService.getPolicyById(id);
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('CUSTOMER')")
    @GetMapping("/byCustomer/{CustomerId}")
    public ResponseEntity<?> getPolicyByCustomerId(@PathVariable Integer CustomerId) {
        List<PolicyResponseDTO> policies = policyService.getPoliciesByCustomerId(CustomerId);
        return new ResponseEntity<>(policies , HttpStatus.OK);
    }



    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<?> updatePolicy(@PathVariable Integer id, @RequestBody PolicyRequestDTO requestDTO) {
        Policy policy = policyService.updatePolicy(id, requestDTO);
        return new ResponseEntity<>(policy, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePolicy(@PathVariable Integer id, @AuthenticationPrincipal User userDetails) {
        boolean policy = policyService.deletePolicy(id, userDetails);
        return new ResponseEntity<>(policy, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/generate/{policyId}")
    public ResponseEntity<byte[]> generatePolicyDocument(@PathVariable Integer policyId, @AuthenticationPrincipal User userDetails) throws IOException{
        byte[] pdfContent = policyDocumentService.generatePolicyDocument(policyId,userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy_document.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}
