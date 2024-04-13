package com.dashboard.user;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainerId;

    private String title;

    private String description;

    private String typeOfWorkout;

    @ElementCollection
    @CollectionTable(name = "workout_video_ids", joinColumns = @JoinColumn(name = "workout_id"))
    private List<String> videoIds;

    @ElementCollection
    @CollectionTable(name = "workout_likes", joinColumns = @JoinColumn(name = "workout_id"))
    private List<String> likes;

    @ElementCollection
    @CollectionTable(name = "workout_dislikes", joinColumns = @JoinColumn(name = "workout_id"))
    private List<String> dislikes;

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and setters, constructors, toString, etc.
}
