package com.search.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainerRepository extends MongoRepository<Trainer, String> {
    List<Trainer> findBySpecialtyContainingIgnoreCase(String specialty);
}
