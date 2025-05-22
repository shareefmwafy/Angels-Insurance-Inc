package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.DTO.Response.AccidentResponse;
import com.asal.insurance_system.Enum.AccidentStatus;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Mapper.AccidentMapper;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.AccidentRepository;
import com.asal.insurance_system.Repository.CustomerRepository;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AuditLogService logService;
    private final AccidentMapper accidentMapper;

    public Accident createAccident(AccidentRequest accidentRequest) {
        Accident accident = accidentMapper.mapToEntityAccident(accidentRequest);

        Customer customer = customerRepository.findById(accidentRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        accident.setCustomer(customer);
        accidentRepository.save(accident);
        logService.logAction(
                "Create Accident",
                "Accident",
                 accident.getId(),
                " ",
                " ",
                customer.getId(),
                "Customer"
        );
        return accident;
    }

    public List<AccidentResponse> getAllAccidents() {

        List<Accident> accidents =  accidentRepository.findAll();
        return accidents.stream()
                .map(accidentMapper::mapToAccidentResponse)
                .collect(Collectors.toList());
    }

    public AccidentResponse getAccidentById(Integer id) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Accident Not Found"));
        return accidentMapper.mapToAccidentResponse(accident);
    }


    public AccidentResponse updateAccidentStatus(Integer id, String status, Integer userId) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Accident Not Found"));

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));

        String oldStatus = accident.getStatus().name();

        if (oldStatus.equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Accident is already in status: " + status);
        }

        try {
            accident.setStatus(AccidentStatus.valueOf(status.toUpperCase()));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid accident status: " + status);
        }
        logService.logAction(
                "Update Accident Status",
                "Accident",
                id,
                oldStatus,
                status,
                userId,
                user.getRole().name()
        );

        return accidentMapper.mapToAccidentResponse(accidentRepository.save(accident));

    }

}
