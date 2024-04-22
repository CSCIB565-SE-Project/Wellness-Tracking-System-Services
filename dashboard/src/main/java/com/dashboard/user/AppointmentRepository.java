package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    Optional<Appointment> findById(Integer id);
    List<Appointment> findByTrainerId(String trainerId);
}

