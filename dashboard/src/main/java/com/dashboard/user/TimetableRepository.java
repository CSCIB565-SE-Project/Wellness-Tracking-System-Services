package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimetableRepository extends MongoRepository<Timetable, ObjectId> {
    Optional<Timetable> findById(String id);
    List<Timetable> findByUserId(Integer userId);
}

