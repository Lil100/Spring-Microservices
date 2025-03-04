package com.schoolmanagement.studentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradingDTO {

    private Long id;
    private String gradeName;
    private Integer minScore;
    private Integer maxScore;
    private String gradePoints; // If needed

    // Constructor with gradeName, minScore, maxScore, and gradePoints
    public GradingDTO(String gradeName, Integer minScore, Integer maxScore, String gradePoints) {
        this.gradeName = gradeName;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.gradePoints = gradePoints; // Initialize gradePoints if needed
    }

    // Optional default constructor
    public GradingDTO() {
    }
}
