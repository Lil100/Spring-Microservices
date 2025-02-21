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
        StudentEntity student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<ExamsEntity> exams = examsRepository.findByStudent(student);

        double totalScore = exams.stream().mapToDouble(ExamsEntity::getScore).sum();
        double averageScore = exams.isEmpty() ? 0 : totalScore / exams.size();

        return "Student: " + student.getName() + " | Average Score: " + averageScore;
    }
}