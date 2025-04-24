package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Model.CancellationRequest;
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

}
