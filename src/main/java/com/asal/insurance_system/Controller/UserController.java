package com.asal.insurance_system.Controller;

import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.CancellationRequest;
import com.asal.insurance_system.Service.ApiResponse;
import com.asal.insurance_system.Service.CancellationRequestService;
import com.asal.insurance_system.Service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final PolicyService policyService;
    private final CancellationRequestService cancellationRequestService;

    public UserController(PolicyService policyService, CancellationRequestService cancellationRequestService) {
        this.policyService = policyService;
        this.cancellationRequestService = cancellationRequestService;
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/approve-cancellation/{requestId}")
    public ResponseEntity<ApiResponse> approveCancellationRequest(@PathVariable Integer requestId) {
        try {
            cancellationRequestService.approveCancellationRequest(requestId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Cancellation request approved successfully", HttpStatus.OK.value()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while approving cancellation request", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/cancellation-requests")
    public ResponseEntity<ApiResponse> getAllCancellationRequests() {
        try {
            List<CancellationRequest> requests = cancellationRequestService.getAllCancellationRequests();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Cancellation requests fetched successfully", HttpStatus.OK.value(), requests));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while fetching cancellation requests", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

}
