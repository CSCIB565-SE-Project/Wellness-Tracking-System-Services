package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {
    Optional<Appointment> findById(Integer id);
    List<Appointment> findByTrainerId(String trainerId);
}

