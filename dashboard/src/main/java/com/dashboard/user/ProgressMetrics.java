package com.dashboard.user;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgressMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;
    private String workoutId;

    private Date date;
    private int caloriesBurnt;
    private int targetCalories;
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
