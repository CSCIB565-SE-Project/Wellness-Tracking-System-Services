package com.dashboard.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressMetricsService {

    @Autowired
    private ProgressMetricsRepository progressMetricsRepository;

    @Autowired
    private UserService userService;

    public List<ProgressMetrics> getAllProgressMetrics() {
        return progressMetricsRepository.findAll();
    }

    public List<ProgressMetrics> getProgressMetricsByUserId(Integer userId) {
        User user = userService.getUserById(userId); // Call the method using injected UserService
        return progressMetricsRepository.findByUser(user);
    }

    public ProgressMetrics createProgressMetrics(ProgressMetrics progressMetrics) {
        return progressMetricsRepository.save(progressMetrics);
    }
}
