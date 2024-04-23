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
@Document
public class Timetable {
    @Id
    private String id;

    private Integer userId;
    
    private String title;
    private LocalDate day;

    private String workout;
    private String meals;
    private String sleepCycle;
    private LocalTime startTime;
    private LocalTime endTime;

}
