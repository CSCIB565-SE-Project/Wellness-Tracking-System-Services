package com.dashboard.user;

import java.util.List;
import java.util.Optional;

public interface ITimetableService {
    List<Timetable> getAllTimetables();
    Optional<Timetable> getTimetableById(Integer id);
    Timetable createTimetable(Timetable timetable);
    Timetable updateTimetable(Integer id, Timetable updatedTimetable);
    void deleteTimetable(Integer id);
}
