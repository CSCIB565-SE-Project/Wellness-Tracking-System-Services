package com.search.user;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "professionals")
public class Trainer {
    @Id
    private String id;
    private String userId;
    private String img;
    private String speciality;
    private String location;
    private String gender;
    private int subscribers;
    private List<String> subscribedUsers;
    private List<String> skills;
}

