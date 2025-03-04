package com.schoolmanagement.studentservice.exams;

import com.schoolmanagement.studentservice.dto.GradingDTO;
import com.schoolmanagement.studentservice.student.StudentEntity;
import com.schoolmanagement.studentservice.student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamsService {

    private final ExamsRepository examsRepository;
    private final StudentRepository studentRepository;

    public ExamsService(ExamsRepository examsRepository,
                        StudentRepository studentRepository) {
        this.examsRepository = examsRepository;
        this.studentRepository = studentRepository;
    }

    public ExamsEntity saveExam(ExamsEntity examsEntity) {
        // Validate and set student
        if (examsEntity.getStudent() == null || examsEntity.getStudent().getId() == null) {
            throw new RuntimeException("Student ID must be provided");
        }

        Long studentId = examsEntity.getStudent().getId();
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        examsEntity.setStudent(student);

        // Predefined grading boundaries
        List<GradingDTO> gradingCriteria = List.of(
                new GradingDTO("F", 1, 39, "0"),
                new GradingDTO("D", 40, 59, "1"),
                new GradingDTO("C", 60, 69, "2"),
                new GradingDTO("B", 70, 79, "3"),
                new GradingDTO("A", 80, 100, "4")
        );

        // Calculate grade based on the score and grading criteria
        String grade = calculateGradeBasedOnScore(examsEntity.getScore(), gradingCriteria);
        if (grade == null) {
            throw new RuntimeException("No grading scale matches the score: " + examsEntity.getScore());
        }

        // Retrieve the corresponding GradingDTO for the calculated grade
        GradingDTO gradingDTO = gradingCriteria.stream()
                .filter(grading -> grading.getGradeName().equals(grade))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Grading scale for grade " + grade + " not found"));

        // Set the grading information in the examsEntity
        examsEntity.setGrading(gradingDTO);

        // Save the exam and return the saved entity
        ExamsEntity savedExam = examsRepository.save(examsEntity);

        return savedExam;
    }

    // Method to calculate grade based on the score
    private String calculateGradeBasedOnScore(double score, List<GradingDTO> gradingCriteria) {
        return gradingCriteria.stream()
                .filter(grading -> score >= grading.getMinScore() && score <= grading.getMaxScore())
                .map(GradingDTO::getGradeName)
                .findFirst()
                .orElse(null); // Return null if no grade matches the score
    }

    // Method to retrieve exams for a specific student
    public List<ExamsEntity> getExamsByStudentId(Long studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        return examsRepository.findByStudent(student);
    }
}
