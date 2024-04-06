package com.loginseervice.login.user;
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
@Document(collection = "trainers")
public class Trainer {
    @Id
    private String id;
    private String userId;
    private String img;
    private int subscribers;
    private List<String> subscribedUsers;
}