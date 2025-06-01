package com.asal.insurance_system.Repository;

import com.asal.insurance_system.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByCustomerId(Integer customerId);
}
