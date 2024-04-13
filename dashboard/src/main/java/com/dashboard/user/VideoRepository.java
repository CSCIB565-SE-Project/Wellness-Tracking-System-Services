package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, Long> {
    List<Video> findByIsApprovedFalse();
    Optional<Video> findById(Integer id);
    void deleteById(String id);
}

