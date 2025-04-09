package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByCustomerIdAndReadFalse(Integer customerId);
}
