package com.dashboard.user;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;
    
    private Date day; 
    private String workout;
    private String meals;
    private String sleepCycle;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    
    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getSleepCycle() {
        return sleepCycle;
    }

    public void setSleepCycle(String sleepCycle) {
        this.sleepCycle = sleepCycle;
    }

    public Integer getUserId() {
            return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }
}