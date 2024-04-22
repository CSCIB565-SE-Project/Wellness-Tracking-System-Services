package com.search.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainerRepository extends MongoRepository<Trainer, String> {
    List<Trainer> findByUserIdContainingIgnoreCaseOrSpecialtyContainingIgnoreCaseOrGenderContainingIgnoreCaseOrLocationContainingIgnoreCase(String userId, String specialty, String gender, String location);
}
