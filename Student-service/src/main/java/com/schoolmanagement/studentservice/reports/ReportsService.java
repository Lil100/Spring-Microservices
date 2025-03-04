package com.schoolmanagement.studentservice.reports;

import com.schoolmanagement.studentservice.exams.ExamsEntity;
import com.schoolmanagement.studentservice.exams.ExamsRepository;
import com.schoolmanagement.studentservice.student.StudentEntity;
import com.schoolmanagement.studentservice.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamsRepository examsRepository;

    public String generateStudentReport(String admissionNumber) {
        // Fetch student by admission number
        StudentEntity student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Fetch exams for the student
        List<ExamsEntity> exams = examsRepository.findByStudent(student);

        // Calculate total score, average score, highest and lowest scores
        double totalScore = exams.stream().mapToDouble(ExamsEntity::getScore).sum();
        double averageScore = exams.isEmpty() ? 0 : totalScore / exams.size();
        double highestScore = exams.stream().mapToDouble(ExamsEntity::getScore).max().orElse(0);
        double lowestScore = exams.stream().mapToDouble(ExamsEntity::getScore).min().orElse(0);

        // Construct the report
        StringBuilder report = new StringBuilder();
        report.append("Student: ").append(student.getName()).append("\n");
        report.append("Admission Number: ").append(student.getAdmissionNumber()).append("\n");
        report.append("Total Exams Taken: ").append(exams.size()).append("\n");
        report.append("Highest Score: ").append(highestScore).append("\n");
        report.append("Lowest Score: ").append(lowestScore).append("\n");
        report.append("Average Score: ").append(String.format("%.2f", averageScore)).append("\n");

        // Add grade breakdown per subject
        report.append("\nSubject Breakdown:\n");
        for (ExamsEntity exam : exams) {
            String grade = calculateGradeBasedOnScore(exam.getScore());
            report.append(exam.getSubject()).append(" - Score: ").append(exam.getScore())
                    .append(" - Grade: ").append(grade).append("\n");
        }

        return report.toString();
    }

    // Method to calculate grade based on score
    private String calculateGradeBasedOnScore(double score) {
        if (score >= 80) return "A";
        if (score >= 70) return "B";
        if (score >= 60) return "C";
        if (score >= 40) return "D";
        return "F";
    }

    public List<ExamsEntity> getStudentExams(String admissionNumber) {
        // Fetch student and their exams
        StudentEntity student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return examsRepository.findByStudent(student);
    }
}
