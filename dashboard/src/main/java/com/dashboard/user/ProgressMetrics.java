package com.dashboard.user;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class ProgressMetrics {

    @Id
    private ObjectId id;

    private Integer userId;
    private String workoutId;

    private Date date;
    private int caloriesBurnt;
    private int targetCalories;
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
