package com.elearning.graduation.model;

import java.time.LocalDateTime;

public class GeneralResponse<T> {

    private EResponseStatus status;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public GeneralResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public GeneralResponse(T data, String message, EResponseStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }


    public static <T> GeneralResponse<T> success(T data, String message) {
        return new GeneralResponse<>(data, message, EResponseStatus.ACCEPTED);
    }

    public static <T> GeneralResponse<T> created(T data, String message) {
        return new GeneralResponse<>(data, message, EResponseStatus.CREATED);
    }

    public static <T> GeneralResponse<T> error(String message, EResponseStatus status) {
        return new GeneralResponse<>(null, message, status);
    }

    public static <T> GeneralResponse<T> notFound(String message) {
        return new GeneralResponse<>(null, message, EResponseStatus.NOT_FOUND);
    }

    public static <T> GeneralResponse<T> badRequest(String message) {
        return new GeneralResponse<>(null, message, EResponseStatus.BAD_REQUEST);
    }


    public static <T> GeneralResponse<T> Unauthorized(String message) {
        return new GeneralResponse<>(null, message, EResponseStatus.UNAUTHORIZED);
    }



    public EResponseStatus getStatus() { 
        return status; 
    }

    public void setStatus(EResponseStatus status) { 
        this.status = status; 
    }

    public String getMessage() { 
        return message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }

    public T getData() { 
        return data; 
    }

    public void setData(T data) { 
        this.data = data; 
    }

    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }

    public void setTimestamp(LocalDateTime timestamp) { 
        this.timestamp = timestamp; 
    }
}