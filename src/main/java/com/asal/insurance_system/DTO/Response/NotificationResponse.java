package com.asal.insurance_system.DTO.Response;

import com.asal.insurance_system.Model.Customer;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NotificationResponse {
    private String title;
    private String message;

    private LocalDate date = LocalDate.now();

    private boolean read = false;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
