package com.asal.insurance_system.Exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponseUtil {
    public static Map<String, Object> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", status.value());
        errorMap.put("error", status.getReasonPhrase());
        errorMap.put("message", message);
        return errorMap;
    }
}
