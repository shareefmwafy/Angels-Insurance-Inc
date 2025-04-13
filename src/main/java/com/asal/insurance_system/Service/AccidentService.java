package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.DTO.Response.AccidentResponse;
import com.asal.insurance_system.Enum.AccidentStatus;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Mapper.AccidentMapper;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Repository.AccidentRepository;
import com.asal.insurance_system.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private final AccidentMapper accidentMapper;

    @Autowired
    public AccidentService(AccidentRepository accidentRepository, CustomerRepository customerRepository, AccidentMapper accidentMapper) {
        this.accidentRepository = accidentRepository;
        this.customerRepository = customerRepository;
        this.accidentMapper = accidentMapper;
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

    public List<AccidentResponse> getAllAccidents() {

        List<Accident> accidents =  accidentRepository.findAll();
        return accidents.stream()
                .map(accidentMapper::mapToAccidentResponse)
                .collect(Collectors.toList());

    }

    public AccidentResponse getAccidentById(Integer id) {
        Accident accident = accidentRepository.findById(id).orElse(null);
        return accidentMapper.mapToAccidentResponse(accident);
    }


    public AccidentResponse updateAccidentStatus(Integer id, String status) {
        Accident accident = accidentRepository.findById(id).orElse(null);

        if (accident != null) {
            accident.setStatus(AccidentStatus.valueOf(status));
            return accidentMapper.mapToAccidentResponse(accidentRepository.save(accident));
        }
        return null;

    }

}
