package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.Request.ClaimRequest;
import com.asal.insurance_system.DTO.Request.ClaimStatusRequest;
import com.asal.insurance_system.DTO.Response.ClaimResponse;
import com.asal.insurance_system.Enum.EnumClaimStatus;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Service.ApiResponse;
import com.asal.insurance_system.Service.ClaimsService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/claim")
public class ClaimsController {


    private final ClaimsService claimsService;

    public ClaimsController(ClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{customerId}")
    public ResponseEntity<ApiResponse<ClaimResponse>> requestClaim(
            @PathVariable Integer customerId,
            @Valid @RequestBody ClaimRequest ClaimRequest,
            @AuthenticationPrincipal Customer customerDetails){

        Integer customerLoggedInId = customerDetails.getId();
        ClaimResponse claimResponse =claimsService.createNewClaim(customerId,ClaimRequest,customerLoggedInId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Successfully Created New Claim",
                        HttpStatus.CREATED.value(),
                        claimResponse
                )
        );
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customerId")
    public ResponseEntity<ApiResponse<List<ClaimResponse>>> getClaimsByCustomer(
            @PathVariable Integer customerId,
            @AuthenticationPrincipal Customer customerDetails){

        Integer customerLoggedInId = customerDetails.getId();
        if (!customerId.equals(customerLoggedInId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ApiResponse<>(
                            "You Can't Access Claims for Other Customers",
                            HttpStatus.FORBIDDEN.value()
                    )
            );
        }
        List<ClaimResponse> claims = claimsService.getClaimsByCustomer(customerId);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Claims Retrived Successfully",
                        HttpStatus.OK.value(),
                        claims
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/status/{claimId}")
    public ResponseEntity<ApiResponse<ClaimResponse>> updateClaimStatus(
            @PathVariable Integer claimId,
            @RequestBody ClaimStatusRequest claimStatusRequest){
        try{
            ClaimResponse claimResponse = claimsService.updateClaimStatus(claimId, claimStatusRequest);
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            "Claim status updated successfully",
                            HttpStatus.OK.value(),
                            claimResponse
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "Invalid claim status value: "+e.getMessage() ,
                            HttpStatus.BAD_REQUEST.value(),
                            null
                    )
            );
        }
    }

}
