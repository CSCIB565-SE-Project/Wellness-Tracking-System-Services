package com.loginseervice.login.registration;

import java.util.Date;

public record RegistrationRequest(String fname, 
    String mname, 
    String lname,
    Date dob,
    String gender,
    String username,
    String email,
    String password,
    String role) {
    
}
