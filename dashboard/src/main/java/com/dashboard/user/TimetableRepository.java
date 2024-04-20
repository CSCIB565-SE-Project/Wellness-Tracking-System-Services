package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimetableRepository extends MongoRepository<Timetable, Integer> {
    Optional<Timetable> findById(Integer id);
    List<Timetable> findByUserId(Integer userId);
}
