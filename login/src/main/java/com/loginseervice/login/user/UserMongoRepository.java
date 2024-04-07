package com.loginseervice.login.user;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserMongo, String>{
    Optional<UserMongo> findById(Integer id);
    void deleteById(String id);
}
