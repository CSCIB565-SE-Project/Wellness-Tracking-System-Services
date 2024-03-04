package com.loginseervice.login.registration;

import java.util.Date;

public record RegistrationRequest(
    String fname,
    String mname,
    String lname,
    Date dob,
    String gender,
    String username,
    String email,
    String password,
    String role) {

    public RegistrationRequest(String fname,
                               String mname,
                               String lname,
                               Date dob,
                               String gender,
                               String username,
                               String email,
                               String password,
                               String role) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public RegistrationRequest setEmail(String email) {
        return new RegistrationRequest(fname, mname, lname, dob, gender, username, email, password, role);
    }

    // Method to set name
    public RegistrationRequest setName(String name) {
        String[] parts = name.split("\\s+");
        if (parts.length == 3) {
            return new RegistrationRequest(parts[0], parts[1], parts[2], dob, gender, username, email, password, role);
        } else if (parts.length == 2) {
            return new RegistrationRequest(parts[0], "", parts[1], dob, gender, username, email, password, role);
        } else {
            return new RegistrationRequest(name, "", "", dob, gender, username, email, password, role);
        }
    }
}
