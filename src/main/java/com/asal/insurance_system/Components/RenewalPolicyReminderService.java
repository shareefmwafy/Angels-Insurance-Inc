package com.asal.insurance_system.Components;

import com.asal.insurance_system.Model.Customer;
import com.asal.insurance_system.Model.Notification;
import com.asal.insurance_system.Model.Policy;
import com.asal.insurance_system.Repository.NotificationRepository;
import com.asal.insurance_system.Repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RenewalPolicyReminderService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @Scheduled(cron = "${scheduling.cron.dailyNotification}")
    public void generateRenewalNotifications() {
        LocalDate reminderDate = LocalDate.now().plusDays(7);
        List<Policy> expiringPolicies = policyRepository.findByEndDate(reminderDate);

        for (Policy policy : expiringPolicies) {
            Customer customer = policy.getCustomer();
            Notification notification = new Notification();
            notification.setTitle("Policy Renewal Reminder");
            notification.setMessage("Your policy #" + policy.getId() +
                    " will expire on " + policy.getEndDate() + ". Please renew it.");
            notification.setCustomer(customer);
            notificationRepository.save(notification);
        }
    }
}
