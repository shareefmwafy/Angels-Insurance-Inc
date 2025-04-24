package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Response.NotificationResponse;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Mapper.NotificationMapper;
import com.asal.insurance_system.Model.Notification;
import com.asal.insurance_system.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public List<NotificationResponse> findRenewalByCustomerId(Integer customerId, Integer loggedInCustomer) {
        if (customerId.equals(loggedInCustomer)) {

            List<Notification> notifications = notificationRepository.findByCustomerIdAndReadFalse(customerId);
            return notifications.stream()
                    .map(notificationMapper::mapToDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public boolean readNotification(Integer notificationId, Integer loggedInCustomerId) {

            Notification notification = notificationRepository.findById(notificationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        if (notification.getCustomer().getId().equals(loggedInCustomerId)){
            notification.setRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;

    }
}
