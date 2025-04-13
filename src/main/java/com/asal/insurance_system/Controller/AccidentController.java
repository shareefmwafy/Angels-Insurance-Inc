package com.asal.insurance_system.Controller;


import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return new ResponseEntity<>(accident, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<Accident>> getAllAccidents() {
        List<Accident> accidents = accidentService.getAllAccidents();
        return new ResponseEntity<>(accidents, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Accident> getAccidentById(@PathVariable Integer id) {
        Accident accident = accidentService.getAccidentById(id);
        if (accident != null) {
            return new ResponseEntity<>(accident, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<Accident> updateAccidentStatus(@PathVariable Integer id, @RequestParam String status) {
        Accident updatedAccident = accidentService.updateAccidentStatus(id, status);
        if (updatedAccident != null) {
            return new ResponseEntity<>(updatedAccident, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
