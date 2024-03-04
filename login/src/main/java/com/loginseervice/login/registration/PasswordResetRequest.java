package com.loginseervice.login.registration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordResetRequest {
    private String token;
    private String newPassword;
}
