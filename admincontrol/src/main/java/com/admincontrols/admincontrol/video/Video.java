package com.admincontrols.admincontrol.video;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videos")
public class Video {
    @Id
    private String id;
    private String trainerId;
    private String workOutPlanId;
    private String title;
    private String description;
    private String imgUrl;
    private String videoUrl;
    private String modeOfInstruction;
    private String typeOfWorkout;
    private String caloriesBurnt;
    private int views;
    private List<String> tags;
    private List<String> likes;
    private List<String> dislikes;
    private boolean isApproved;
}

