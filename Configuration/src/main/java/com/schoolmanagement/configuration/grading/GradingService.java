package com.schoolmanagement.configuration.grading;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradingService {

    private final GradingRepository gradingRepository;

    public GradingService(GradingRepository gradingRepository) {
        this.gradingRepository = gradingRepository;
    }

    @PostConstruct
    @Transactional
    public void setupGradingCriteria() {
        if (gradingRepository.count() == 0) {  // Check if grading data exists in the database
            // Predefined grading boundaries
            GradingEntity grading1 = new GradingEntity();
            grading1.setGradeName("F");
            grading1.setMinScore(1);
            grading1.setMaxScore(39);
            grading1.setGradePoints("0");

            GradingEntity grading2 = new GradingEntity();
            grading2.setGradeName("D");
            grading2.setMinScore(40);
            grading2.setMaxScore(59);
            grading2.setGradePoints("1");

            GradingEntity grading3 = new GradingEntity();
            grading3.setGradeName("C");
            grading3.setMinScore(60);
            grading3.setMaxScore(69);
            grading3.setGradePoints("2");

            GradingEntity grading4 = new GradingEntity();
            grading4.setGradeName("B");
            grading4.setMinScore(70);
            grading4.setMaxScore(79);
            grading4.setGradePoints("3");

            GradingEntity grading5 = new GradingEntity();
            grading5.setGradeName("A");
            grading5.setMinScore(80);
            grading5.setMaxScore(100);
            grading5.setGradePoints("4");

            // Save to database
            gradingRepository.save(grading1);
            gradingRepository.save(grading2);
            gradingRepository.save(grading3);
            gradingRepository.save(grading4);
            gradingRepository.save(grading5);  // Missing save for the "A" grade
        }
    }

    // Calculate the grade based on score
    public String calculateGrade(Integer score) {
        List<GradingEntity> gradingEntities = gradingRepository.findAll();

        for (GradingEntity grading : gradingEntities) {
            if (score >= grading.getMinScore() && score <= grading.getMaxScore()) {
                return grading.getGradeName();  // Return the grade name
            }
        }
        return "No grade found";  // If no grade is found for the score
    }

    // Fetch all grading criteria
    public List<GradingEntity> getAllGradingCriteria() {
        return gradingRepository.findAll();
    }
}
