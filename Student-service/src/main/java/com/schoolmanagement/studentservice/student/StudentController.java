package com.schoolmanagement.studentservice.student;

import com.schoolmanagement.studentservice.ConfigurationServiceClient;
import com.schoolmanagement.studentservice.dto.ClassesDTO;
import com.schoolmanagement.studentservice.dto.GradingDTO;
import com.schoolmanagement.studentservice.dto.StreamsDTO;
import com.schoolmanagement.studentservice.dto.SubjectsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ConfigurationServiceClient configurationServiceClient;

    @GetMapping("/all")
    public String home() {
        return "Student Service is running!";
    }

    @PostMapping("/add")
    public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentEntity studentEntity) {
        return ResponseEntity.ok(studentService.saveStudent(studentEntity));
    }

    @GetMapping("/{admissionNumber}")
    public ResponseEntity<StudentEntity> getStudent(@PathVariable String admissionNumber) {
        return studentService.getStudentByAdmissionNumber(admissionNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/api/classes/all")
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        return ResponseEntity.ok(configurationServiceClient.getAllClasses());
    }

    @GetMapping("/api/streams/all")
    public ResponseEntity<List<StreamsDTO>> getAllStreams() {
        return ResponseEntity.ok(configurationServiceClient.getAllStreams());
    }

    @GetMapping("/api/subjects/all")
    public ResponseEntity<List<SubjectsDTO>> getAllSubjects() {
        return ResponseEntity.ok(configurationServiceClient.getAllSubjects());
    }

    @GetMapping("/api/grading/all")
    public ResponseEntity<List<GradingDTO>> getAllGrades() {
        return ResponseEntity.ok(configurationServiceClient.getAllGrades());
    }

}
