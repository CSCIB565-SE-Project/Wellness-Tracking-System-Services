package com.dashboard.user;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trainers")
public class Trainer {
    @Id
    private String id;
    private String userId;
    private String img;
    private String specialty;
    private String location;
    private String gender;
    private int subscribers;
    private List<String> subscribedUsers;
    private List<String> skills;
    public String getId() {
        return id;
    }
}
