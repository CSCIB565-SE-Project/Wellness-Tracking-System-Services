package com.dashboard.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalMetricsService {

    @Autowired
    private ProfessionalMetricsRepository professionalMetricsRepository;

    public List<ProfessionalMetrics> getMetricsForProfessional(String professionalId) {
        return professionalMetricsRepository.findByProfessionalId(professionalId);
    }

    // Add other service methods as needed
}
