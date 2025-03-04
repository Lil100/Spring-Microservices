package com.schoolmanagement.studentservice.reports;

import com.schoolmanagement.studentservice.exams.ExamsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportService;

    @GetMapping("/{admissionNumber}")
    public ResponseEntity<String> getReport(@PathVariable String admissionNumber) {
        return ResponseEntity.ok(reportService.generateStudentReport(admissionNumber));
    }
    @GetMapping("/student/{admissionNumber}/exams")
    public List<ExamsEntity> getStudentExams(@PathVariable String admissionNumber) {
        return reportService.getStudentExams(admissionNumber);
    }
}