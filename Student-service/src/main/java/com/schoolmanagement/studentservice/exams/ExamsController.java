package com.schoolmanagement.studentservice.exams;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamsController {

    private final ExamsService examsService;

    // Constructor injection of ExamsService
    public ExamsController(ExamsService examsService) {
        this.examsService = examsService;
    }

    // Endpoint to add an exam
    @PostMapping("/add")
    public ResponseEntity<ExamsEntity> addExam(@RequestBody ExamsEntity examsEntity) {
        try {
            // Call the service method to save the exam
            ExamsEntity savedExam = examsService.saveExam(examsEntity);
            return new ResponseEntity<>(savedExam, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            // Handle exceptions and send an error response if any validation fails
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get exams for a specific student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamsEntity>> getStudentExams(@PathVariable Long studentId) {
        List<ExamsEntity> exams = examsService.getExamsByStudentId(studentId);
        return ResponseEntity.ok(exams);
    }
}
