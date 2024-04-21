package com.loginseervice.login.user;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserMongo {
    @Id
    private String id;
    private String userId;
    private String userEmail;
    private String userFname;
    private String userLName;
    private Date userDoB;
    private String userGender;
    private String img;
    private List<String> subscribedTrainers = new ArrayList<>();;
}
