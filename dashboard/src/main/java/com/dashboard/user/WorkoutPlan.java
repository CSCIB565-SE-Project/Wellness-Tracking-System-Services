package com.dashboard.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class WorkoutPlan {

    @Id
    private ObjectId id;

    private String trainerId;

    private String title;

    private String description;

    private String typeOfWorkout;

    private List<String> videoIds;

    private List<String> likes;

    private List<String> dislikes;

}
