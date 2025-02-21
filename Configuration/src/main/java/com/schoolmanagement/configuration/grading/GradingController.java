package com.schoolmanagement.configuration.grading;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grading")
public class GradingController {

    private final GradingService gradingService;

    public GradingController(GradingService gradingService) {
        this.gradingService = gradingService;
    }

    @GetMapping("/all")
    public List<GradingEntity> getAllGradingCriteria() {
        return gradingService.getAllGradingCriteria();
    }

    // Endpoint to calculate grade for a specific score
    @GetMapping("/calculate")
    public String calculateGrade(Integer score) {
        return gradingService.calculateGrade(score);
    }
}
