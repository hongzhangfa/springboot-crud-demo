package com.camphor.springbootproject.service;

public class UserNoFoundException extends Throwable {
    public UserNoFoundException(String msg) {
        super(msg);
    }
}


