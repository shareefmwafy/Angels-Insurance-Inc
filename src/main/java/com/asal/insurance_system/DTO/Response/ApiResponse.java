package com.asal.insurance_system.DTO.Response;

public class ApiResponse<T> {
    private String message;
    private int status;
    private T data;

    public ApiResponse(){

    }
    public ApiResponse(String message, int statusCode) {
        this.message = message;
        this.status = statusCode;
    }

    public ApiResponse(String message, int statusCode, T data) {
        this.message = message;
        this.status = statusCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return status;
    }

    public void setStatusCode(int statusCode) {
        this.status = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
