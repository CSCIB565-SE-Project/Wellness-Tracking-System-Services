package com.dashboard.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgressMetricsRepository extends MongoRepository<ProgressMetrics, Long> {
    List<ProgressMetrics> findByUserId(Integer userId);
}