package com.schoolmanagement.studentservice.exams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamsController {

    @Autowired
    private ExamsService examsService;

    @PostMapping("/add")
    public ResponseEntity<ExamsEntity> addExam(@RequestBody ExamsEntity examsEntity) {
        return ResponseEntity.ok(examsService.saveExam(examsEntity));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamsEntity>> getStudentExams(@PathVariable Long studentId) {
        return ResponseEntity.ok(examsService.getExamsByStudent(studentId));
    }
}