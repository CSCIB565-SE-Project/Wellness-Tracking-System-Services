package com.dashboard.user;

import java.util.List;
import java.util.Optional;


public interface ITimetableService {
    List<Timetable> getAllTimetables();
    Optional<Timetable> getTimetableById(String id);
    Timetable createTimetable(Timetable timetable);
    Timetable updateTimetable(String id, Timetable updatedTimetable);
    List<Timetable> getTimetablesByUserId(Integer userId); 
    Timetable createTimetableForUser(Integer userId, Timetable timetable);
    boolean doesTimetableBelongToUser(Integer userId, String timetableId);
    void deleteTimetable(String id);
}
