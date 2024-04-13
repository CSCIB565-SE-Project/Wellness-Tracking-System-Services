package com.dashboard.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutPlanRepository extends MongoRepository<WorkoutPlan, Long> {
    // You can define custom query methods if needed
}
