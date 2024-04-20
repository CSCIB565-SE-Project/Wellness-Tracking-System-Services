package com.dashboard.user;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainerId;

    private String title;

    private String description;

    private String typeOfWorkout;

    private List<String> videoIds;

    private List<String> likes;

    private List<String> dislikes;

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and setters, constructors, toString, etc.
}
