package com.dashboard.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.ProgressMetrics;
import com.dashboard.user.ProgressMetricsService;

@RestController
@RequestMapping("/progress-metrics")
public class ProgressMetricsController {

    @Autowired
    private ProgressMetricsService progressMetricsService;

    @GetMapping
    public ResponseEntity<List<ProgressMetrics>> getAllProgressMetrics() {
        List<ProgressMetrics> progressMetricsList = progressMetricsService.getAllProgressMetrics();
        return new ResponseEntity<>(progressMetricsList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgressMetrics> createProgressMetrics(@RequestBody ProgressMetrics progressMetrics) {
        ProgressMetrics createdProgressMetrics = progressMetricsService.createProgressMetrics(progressMetrics);
        return new ResponseEntity<>(createdProgressMetrics, HttpStatus.CREATED);
    }

    // Other endpoints for updating, deleting, or fetching specific progress metrics
}

