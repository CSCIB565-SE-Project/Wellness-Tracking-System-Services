package com.loginseervice.login.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.List;


public interface TrainerRepository extends MongoRepository<Trainer, String>{
    Optional<Trainer> findById(Integer id);
    void deleteById(String id);
    List<Trainer> findBySkillsContaining(String skillTag);
}
