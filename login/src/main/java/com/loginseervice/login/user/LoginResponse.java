package com.loginseervice.login.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    String message;
    Boolean status;
    User user;

    public LoginResponse(String message, Boolean status, User user){
        this.message = message;
        this.status = status;
        this.user = user;
    }
}
