package com.dashboard.user;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Document(collection="mealplan")
public class Mealplan {

    @Id
    private String id;

    private Integer userId;
    private String title;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;

}
