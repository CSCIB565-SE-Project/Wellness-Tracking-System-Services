package com.dashboard.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, Integer> {
    Optional<Appointment> findById(Integer id);
}

