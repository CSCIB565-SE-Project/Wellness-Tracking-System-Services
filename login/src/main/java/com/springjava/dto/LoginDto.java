package com.springjava.dto;

public class LoginDto {
    private String username;
    private String password;
    public LoginDto() {}
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
