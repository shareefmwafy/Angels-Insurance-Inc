package com.asal.insurance_system.Controller;


import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.DTO.Response.AccidentResponse;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Service.AccidentService;
import com.asal.insurance_system.Service.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @Autowired
    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("")
    public ResponseEntity<Accident> createAccident(@RequestBody AccidentRequest accidentRequest) {
        Accident accident = accidentService.createAccident(accidentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<AccidentResponse>> getAllAccidents() {
        List<AccidentResponse> accidents = accidentService.getAllAccidents();
        return new ResponseEntity<>(accidents, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccidentResponse>> getAccidentById(@PathVariable Integer id) {
        AccidentResponse accident = accidentService.getAccidentById(id);
        if (accident != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Accident Found Successfully",
                            HttpStatus.OK.value(),
                            accident
                    )
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            "Accident Not Found",
                            HttpStatus.NOT_FOUND.value()

                    )
            );
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<AccidentResponse> updateAccidentStatus(@PathVariable Integer id, @RequestParam String status, @AuthenticationPrincipal User userDetails) {
        AccidentResponse updatedAccident = accidentService.updateAccidentStatus(id, status,userDetails.getId());
        if (updatedAccident != null) {
            return new ResponseEntity<>(updatedAccident, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
