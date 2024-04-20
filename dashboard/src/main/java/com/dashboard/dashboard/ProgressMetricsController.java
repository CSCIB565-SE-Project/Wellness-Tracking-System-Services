package com.dashboard.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.ProgressMetrics;
import com.dashboard.user.ProgressMetricsService;

@RestController
@RequestMapping("/progress-metrics")
@CrossOrigin(origins = "http://localhost:3000")
public class ProgressMetricsController {

    @Autowired
    private ProgressMetricsService progressMetricsService;

    @GetMapping("/get")
    public ResponseEntity<List<ProgressMetrics>> getAllProgressMetrics(@RequestParam Integer userId) {
        List<ProgressMetrics> progressMetricsList = progressMetricsService.getAllProgressMetrics(userId);
        return new ResponseEntity<>(progressMetricsList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProgressMetrics> createProgressMetrics(@RequestParam Integer userId, @RequestBody ProgressMetrics progressMetrics) {
        progressMetrics.setUserId(userId); // Set the userId for the progress metrics
        ProgressMetrics createdProgressMetrics = progressMetricsService.createProgressMetrics(userId, progressMetrics);
        return new ResponseEntity<>(createdProgressMetrics, HttpStatus.CREATED);
    }
}
