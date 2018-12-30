package com.hinawi.api.controller;

public class ResourseNotFoundException extends RuntimeException{

    ResourseNotFoundException(String message) {
        super(message.toUpperCase());

//        String x=message;
//        this.getMessage();
    }
}
