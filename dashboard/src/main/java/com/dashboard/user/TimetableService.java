package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimetableService implements ITimetableService {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;
    

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    public Optional<Timetable> getTimetableById(Integer id) {
        return timetableRepository.findById(id);
    }

    public Timetable createTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    public Timetable updateTimetable(Integer id, Timetable updatedTimetable) {
        Optional<Timetable> timetableOptional = timetableRepository.findById(id);
        if (timetableOptional.isPresent()) {
            Timetable timetable = timetableOptional.get();
            timetable.setWorkout(updatedTimetable.getWorkout());
            timetable.setMeals(updatedTimetable.getMeals());
            timetable.setSleepCycle(updatedTimetable.getSleepCycle());
            timetable.setDay(updatedTimetable.getDay());

            return timetableRepository.save(timetable);
        } else {
            throw new RuntimeException("Timetable not found with id: " + id);
        }
    }

    public List<Timetable> getTimetablesByUserId(Integer userId) {
        return timetableRepository.findByUserId(userId);
    }

    public Timetable createTimetableForUser(Integer userId, Timetable timetable) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        timetable.setUser(user);
        return timetableRepository.save(timetable);
    }

    public boolean doesTimetableBelongToUser(Integer userId, Integer timetableId) {
        Optional<Timetable> timetableOptional = timetableRepository.findById(timetableId);
        return timetableOptional.map((Timetable timetable) -> timetable.getUserId().equals(userId)).orElse(false);
    }
    

    public void deleteTimetable(Integer id) {
        timetableRepository.deleteById(id);
    }
}
