
package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.Request.PolicyCancellationReason;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.CancellationRequest;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.CancellationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cancellation-requests")
public class RequestCancellation {

    private final CancellationRequestService cancellationRequestService;

    public RequestCancellation(CancellationRequestService cancellationRequestService) {
        this.cancellationRequestService = cancellationRequestService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/approve-cancellation/{requestId}")
    public ResponseEntity<ApiResponse> approveCancellationRequest(@PathVariable Integer requestId, @AuthenticationPrincipal User userDetails) {
        cancellationRequestService.approveCancellationRequest(requestId,userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Cancellation request approved successfully", HttpStatus.OK.value()));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{policyId}")
    public ResponseEntity<ApiResponse> requestPolicyCancellation(@PathVariable Integer policyId, @RequestBody PolicyCancellationReason reason, @AuthenticationPrincipal Customer userDetails) {

        Integer customerId = userDetails.getId();
        ApiResponse<CancellationRequest> result = cancellationRequestService.requestPolicyCancellation(policyId, reason.getReason(), customerId);
        return ResponseEntity
                .status(result.getStatusCode())
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllCancellationRequests() {
        List<CancellationRequest> requests = cancellationRequestService.getAllCancellationRequests();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Cancellation requests fetched successfully", HttpStatus.OK.value(), requests));
    }

}