package com.asal.insurance_system.Controller;

import com.asal.insurance_system.DTO.Response.NotificationResponse;
import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Notification;
import com.asal.insurance_system.Repository.NotificationRepository;
import com.asal.insurance_system.DTO.Response.ApiResponse;
import com.asal.insurance_system.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{customerId}")
    public ResponseEntity<List<NotificationResponse>> getCustomerNotifications(@PathVariable Integer customerId, @AuthenticationPrincipal Customer userDetails) {
        Integer loggedInCustomer = userDetails.getId();
        return ResponseEntity.ok(notificationService.findRenewalByCustomerId(customerId,loggedInCustomer));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/read/{notificationId}")
    public ResponseEntity<?> markAsRead(@PathVariable Integer notificationId, @AuthenticationPrincipal Customer userDetails) {
        Integer loggedInCustomerId = userDetails.getId();
        System.out.println(userDetails);
        boolean result = notificationService.readNotification(notificationId,loggedInCustomerId);
        if (result){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Notification>(
                "this notification was read",
                HttpStatus.OK.value()
            ));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
