package com.dashboard.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.ProfessionalMetrics;
import com.dashboard.user.ProfessionalMetricsService;

@RestController
@RequestMapping("/api/professional-metrics")
public class ProfessionalMetricsController {

    @Autowired
    private ProfessionalMetricsService professionalMetricsService;

    @GetMapping("/{professionalId}")
    public ResponseEntity<List<ProfessionalMetrics>> getMetricsForProfessional(@PathVariable Long professionalId) {
        List<ProfessionalMetrics> metrics = professionalMetricsService.getMetricsForProfessional(professionalId);
        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

    // Add other endpoints for adding, updating, or deleting metrics
}

