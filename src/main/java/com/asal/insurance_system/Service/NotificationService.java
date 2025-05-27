package com.asal.insurance_system.Service;

import com.asal.insurance_system.DTO.Response.NotificationResponse;
import com.asal.insurance_system.Exception.ResourceNotFoundException;
import com.asal.insurance_system.Mapper.NotificationMapper;
import com.asal.insurance_system.Model.Notification;
import com.asal.insurance_system.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationResponse> findRenewalByCustomerId(Integer customerId, Integer loggedInCustomer) {
        if (customerId.equals(loggedInCustomer)) {

            List<Notification> notifications = notificationRepository.findByCustomerId(customerId);
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
