package com.hinawi.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private int status;
    private String message;
    private Object result;
    private boolean success;

    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(int status, String message, Object result,boolean success) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.success=success;
    }
}
