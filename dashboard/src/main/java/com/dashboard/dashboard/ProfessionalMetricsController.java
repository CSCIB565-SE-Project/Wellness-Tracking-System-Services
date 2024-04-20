package com.dashboard.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.ProfessionalMetrics;
import com.dashboard.user.ProfessionalMetricsService;

@RestController
@RequestMapping("/professional-metrics")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessionalMetricsController {

    @Autowired
    private ProfessionalMetricsService professionalMetricsService;

    @GetMapping ("/get")
    public ResponseEntity<List<ProfessionalMetrics>> getMetricsForProfessional(@RequestParam String trainerId) {
        List<ProfessionalMetrics> metrics = professionalMetricsService.getMetricsForProfessional(trainerId);
        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

}
