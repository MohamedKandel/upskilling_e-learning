package com.example.elearning.Responses;


import com.example.elearning.Enum.ResponseStatus;

import java.net.http.HttpResponse;

public class GeneralResponse <T> {

    private ResponseStatus response; //this is ResponseStatus(Enum we created )
    private String message;
    private  T data;

    public GeneralResponse() {
    }

    public GeneralResponse(ResponseStatus response, T data) {
        this.response = response;
        this.data = data;
    }

    public GeneralResponse(ResponseStatus response, String message, T data) {
        this.response = response;
        this.message = message;
        this.data = data;
    }

    public ResponseStatus getResponse() {
        return response;
    }

    public void setResponse(ResponseStatus response) {
        this.response = response;
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
}



