package com.admincontrols.admincontrol.video;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface VideoRepository extends MongoRepository<Video, String> {
    List<Video> findByIsApprovedFalse();
    Optional<Video> findById(Integer id);
    void deleteById(String id);
}
