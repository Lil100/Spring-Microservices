package com.schoolmanagement.studentservice.student;

import com.schoolmanagement.studentservice.dto.ClassesDTO;
import com.schoolmanagement.studentservice.dto.GradingDTO;
import com.schoolmanagement.studentservice.dto.StreamsDTO;
import com.schoolmanagement.studentservice.dto.SubjectsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint to add a new student
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentEntity studentEntity) {
        try {
            StudentEntity savedStudent = studentService.saveStudent(studentEntity);
            return ResponseEntity.ok(savedStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get student by admission number
    @GetMapping("/{admissionNumber}")
    public ResponseEntity<?> getStudentByAdmissionNumber(@PathVariable String admissionNumber) {
        Optional<StudentEntity> student = studentService.getStudentByAdmissionNumber(admissionNumber);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all available classes (Forms)
    @GetMapping("/classes")
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        return ResponseEntity.ok(studentService.getAllClasses());
    }

    // Get all available streams
    @GetMapping("/streams")
    public ResponseEntity<List<StreamsDTO>> getAllStreams() {
        return ResponseEntity.ok(studentService.getAllStreams());
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectsDTO>> getAllSubjects() {
        return ResponseEntity.ok(studentService.getAllSubjects());
    }

    @GetMapping("/grade")
    public ResponseEntity<List<GradingDTO>> getAllGrades() {
        return ResponseEntity.ok(studentService.getAllGrades());
    }

}
