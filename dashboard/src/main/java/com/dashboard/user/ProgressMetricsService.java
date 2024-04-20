package com.dashboard.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressMetricsService {

    @Autowired
    private ProgressMetricsRepository progressMetricsRepository;

    public List<ProgressMetrics> getAllProgressMetrics(Integer userId) {
        return progressMetricsRepository.findByUserId(userId); // Use userId to filter progress metrics
    }

    public ProgressMetrics createProgressMetrics(Integer userId, ProgressMetrics progressMetrics) {
        // Set the userId on the progressMetrics object before saving
        progressMetrics.setUserId(userId);
        return progressMetricsRepository.save(progressMetrics);
    }
}
