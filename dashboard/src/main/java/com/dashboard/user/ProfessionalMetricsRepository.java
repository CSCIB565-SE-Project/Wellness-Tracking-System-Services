package com.dashboard.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessionalMetricsRepository extends MongoRepository<ProfessionalMetrics, ObjectId> {
    List<ProfessionalMetrics> findByProfessionalId(String professionalId);
}

