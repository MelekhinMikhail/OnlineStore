package com.example.onlinestore.util;

public class NoAccessRightException extends RuntimeException {

    public NoAccessRightException(String message) {
        super(message);
    }
}
