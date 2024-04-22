package com.dashboard.user;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class Timetable {
    @Id
    private String id;

    private Integer userId;
    
    private String title;
    private LocalDate day;

    private String workout;
    private String meals;
    private String sleepCycle;
    private LocalTime startTime;
    private LocalTime endTime;

    // Getters and setters
    // Note: You can generate these using your IDE to avoid errors.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}
