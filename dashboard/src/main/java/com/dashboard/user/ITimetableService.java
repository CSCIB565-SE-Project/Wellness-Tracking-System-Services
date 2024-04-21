package com.dashboard.user;

import java.util.List;
import java.util.Optional;

public interface ITimetableService {
    List<Timetable> getAllTimetables();
    Optional<Timetable> getTimetableById(Integer id);
    Timetable createTimetable(Timetable timetable);
    Timetable updateTimetable(Integer id, Timetable updatedTimetable);
    List<Timetable> getTimetablesByUserId(Integer userId); 
    Timetable createTimetableForUser(Integer userId, Timetable timetable);
    boolean doesTimetableBelongToUser(Integer userId, Integer timetableId);
    void deleteTimetable(Integer id);
}
