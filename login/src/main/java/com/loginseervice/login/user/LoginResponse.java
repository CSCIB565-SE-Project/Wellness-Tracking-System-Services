package com.loginseervice.login.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    String message;
    Boolean status;
    String token;
    User user;

    public LoginResponse(String message, Boolean status, User user, String token){
        this.message = message;
        this.status = status;
        this.token = token;
        this.user = user;
    }
}
