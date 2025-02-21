package com.schoolmanagement.studentservice.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportService;

    @GetMapping("/{admissionNumber}")
    public ResponseEntity<String> getReport(@PathVariable String admissionNumber) {
        return ResponseEntity.ok(reportService.generateStudentReport(admissionNumber));
    }
}