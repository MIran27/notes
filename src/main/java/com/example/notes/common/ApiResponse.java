package com.example.notes.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author miran_jayasinghe
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private int status;
    private String message;
    private Object result;
    public ApiResponse(){

    }
    public ApiResponse(int status, String message, Object result){
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}