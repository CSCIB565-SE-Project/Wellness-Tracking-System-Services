package com.dashboard.user;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TrainerRepository extends MongoRepository<Trainer, String>{
    Optional<Trainer> findById(Integer id);
    void deleteById(Integer id);
    List<Trainer> findBySkillsContains(String skillTag);
}
