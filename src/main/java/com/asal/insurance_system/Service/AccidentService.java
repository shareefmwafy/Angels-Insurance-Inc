package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.Enum.AccidentStatus;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Repository.AccidentRepository;
import com.asal.insurance_system.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public Accident createAccident(AccidentRequest accidentRequest) {
        Accident accident = new Accident();
        accident.setAccidentDate(accidentRequest.getAccidentDate());
        accident.setLocation(accidentRequest.getLocation());
        accident.setDescription(accidentRequest.getDescription());
        accident.setDocuments(accidentRequest.getDocuments());
        Customer customer = customerRepository.findById(accidentRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        accident.setCustomer(customer);
        return accidentRepository.save(accident);
    }

    public List<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    public Accident getAccidentById(Integer id) {
        return accidentRepository.findById(id).orElse(null);
    }


    public Accident updateAccidentStatus(Integer id, String status) {
        Accident accident = accidentRepository.findById(id).orElse(null);
        if (accident != null) {
            accident.setStatus(AccidentStatus.valueOf(status));
            return accidentRepository.save(accident);
        }
        return null;
    }

}
