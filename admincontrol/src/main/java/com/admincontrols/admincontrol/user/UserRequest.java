package com.admincontrols.admincontrol.user;

public record UserRequest(
    Integer id,
    String fname,
    String mname,
    String lname,
    String username,
    String email,
    String token,
    String role,
    boolean isEnabled){}