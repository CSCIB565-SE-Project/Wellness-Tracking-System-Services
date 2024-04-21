package com.dashboard.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgressMetricsRepository extends MongoRepository<ProgressMetrics, ObjectId> {
    List<ProgressMetrics> findByUserId(Integer userId);
}