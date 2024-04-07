package com.loginseervice.login.exception;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(String message) {
        super(message);
    }
}