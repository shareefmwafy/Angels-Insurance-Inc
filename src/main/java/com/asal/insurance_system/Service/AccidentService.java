package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Request.AccidentRequest;
import com.asal.insurance_system.DTO.Response.AccidentResponse;
import com.asal.insurance_system.Enum.AccidentStatus;
import com.asal.insurance_system.Mapper.AccidentMapper;
import com.asal.insurance_system.Model.Accident;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.AccidentRepository;
import com.asal.insurance_system.Repository.CustomerRepository;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AuditLogService logService;
    private final AccidentMapper accidentMapper;

    public Accident createAccident(AccidentRequest accidentRequest) {
        Accident accident = new Accident();
        accident.setAccidentDate(accidentRequest.getAccidentDate());
        accident.setLocation(accidentRequest.getLocation());
        accident.setDescription(accidentRequest.getDescription());
        accident.setDocuments(accidentRequest.getDocuments());
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
        Accident accident = accidentRepository.findById(id).orElse(null);
        return accidentMapper.mapToAccidentResponse(accident);
    }


    public AccidentResponse updateAccidentStatus(Integer id, String status, Integer userId) {
        Accident accident = accidentRepository.findById(id).orElse(null);
        Optional<User> user = userRepository.findById(userId);


        if (accident != null) {
            logService.logAction(
                    "Update Accident Status",
                    "Accident",
                    id,
                    String.valueOf(accident.getStatus()),
                    status,
                    userId,
                    user.get().getRole().name()
            );
            accident.setStatus(AccidentStatus.valueOf(status));

            return accidentMapper.mapToAccidentResponse(accidentRepository.save(accident));
        }
        return null;

    }

}
