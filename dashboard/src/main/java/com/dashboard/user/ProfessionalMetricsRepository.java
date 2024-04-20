package com.dashboard.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessionalMetricsRepository extends MongoRepository<ProfessionalMetrics, Long> {
    List<ProfessionalMetrics> findByProfessionalId(String professionalId);
}

