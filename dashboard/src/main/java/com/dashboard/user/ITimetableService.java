package com.dashboard.user;

import java.util.List;
import java.util.Optional;

public interface ITimetableService {
    List<Timetable> getAllTimetables();
    Optional<Timetable> getTimetableById(Integer id);
    Timetable createTimetable(Timetable timetable);
    Timetable updateTimetable(Integer id, Timetable updatedTimetable);
    List<Timetable> getTimetablesByUserId(String userId); // Changed parameter type to String
    Timetable createTimetableForUser(String userId, Timetable timetable); // Changed parameter type to String
    boolean doesTimetableBelongToUser(Integer userId, Integer timetableId);
    void deleteTimetable(Integer id);
}
