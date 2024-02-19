package com.springjava.dto;

import java.sql.Date;

public class SignUpDto{
    private String fname;
    private String mname;
    private String lname;
    private Date dob;
    private String gender;
    private String email;
    private String username;
    private String password;

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }
    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDoB() {
        return dob;
    }
    public void setDoB(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}