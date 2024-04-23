package com.loginseervice.login.user;

import java.util.ArrayList;
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
@Document(collection = "trainers")
public class Trainer {
    @Id
    private String id;
    private String userId;
    private String firstName;
    private String username;
    private String img;
    private String specialty;
    private String location;
    private String gender;
    private int subscribers;
    private List<String> subscribedUsers = new ArrayList<>();
    private List<String> skills;
}
