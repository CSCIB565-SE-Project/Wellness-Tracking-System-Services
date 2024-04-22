package com.loginseervice.login.registration;

import java.util.Date;
import java.util.List;

public record RegistrationRequest(
    String fname,
    String mname,
    String lname,
    Date dob,
    String gender,
    String username,
    String email,
    String password,
    String role,
    List<String> skills,
    String speciality,
    String location,
    Boolean isOAuth) {

    public RegistrationRequest(String fname,
                               String mname,
                               String lname,
                               Date dob,
                               String gender,
                               String username,
                               String email,
                               String password,
                               String role,
                               List<String> skills,
                               String speciality,
                               String location,
                               Boolean isOAuth) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.skills = skills;
        this.speciality = speciality;
        this.location = location;
        this.isOAuth = isOAuth;
    }

    public RegistrationRequest setEmail(String email) {
        return new RegistrationRequest(fname, mname, lname, dob, gender, username, email, password, role, skills, speciality, location, isOAuth);
    }

    // Method to set name
    public RegistrationRequest setName(String name) {
        String[] parts = name.split("\\s+");
        if (parts.length == 3) {
            return new RegistrationRequest(parts[0], parts[1], parts[2], dob, gender, username, email, password, role, skills, speciality, location, isOAuth);
        } else if (parts.length == 2) {
            return new RegistrationRequest(parts[0], "", parts[1], dob, gender, username, email, password, role, skills, speciality, location, isOAuth);
        } else {
            return new RegistrationRequest(name, "", "", dob, gender, username, email, password, role, skills, speciality, location, isOAuth);
        }
    }
}
