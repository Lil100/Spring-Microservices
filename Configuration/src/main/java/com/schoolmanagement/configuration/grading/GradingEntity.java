package com.schoolmanagement.configuration.grading;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GradingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gradeName;    // Grade name (e.g., "A", "B", etc.)
    private Integer minScore;    // Minimum score for this grade
    private Integer maxScore;    // Maximum score for this grade
    private String gradePoints;  // Grade points (e.g., 12 for "A")

}
