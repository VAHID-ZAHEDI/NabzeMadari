package com.example.pregnancykotlin.models;

public class ErrorModel {
     String Message;
     int httpErrorCode;

    public ErrorModel(String message) {
        Message = message;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public ErrorModel(String message, int httpErrorCode) {
        Message = message;
        this.httpErrorCode = httpErrorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
