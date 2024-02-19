package com.springjava.entity;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fname;
    @Column(nullable = true)
    private String mname;
    @Column(nullable = false)
    private String lname;
    @Column(nullable = false)
    private Date dob;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Role role;
    private EntityManager entityManager;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public String getFname(){
        return fname;
    }

    public void setMname(String mname){
        this.mname = mname;
    }

    public String getMname(){
        return mname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public String getLname(){
        return lname;
    }
    
    public void setDoB(Date dob){
        this.dob = dob;
    }

    public Date getDoB(){
        return dob;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getFnameById(Integer id){
        User user = entityManager.find(User.class, id);
        if(user != null){
            return user.getFname();
        }
        else{
           return null; 
        }
    }    
}
