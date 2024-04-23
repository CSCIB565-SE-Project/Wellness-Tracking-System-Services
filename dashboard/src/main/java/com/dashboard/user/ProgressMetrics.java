package com.dashboard.user;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="progressmetrics")
public class ProgressMetrics {

    @Id
    private ObjectId id;

    private Integer userId;
    private String workoutId;

    private Date date;
    private int caloriesBurnt;
    private int targetCalories;

}
