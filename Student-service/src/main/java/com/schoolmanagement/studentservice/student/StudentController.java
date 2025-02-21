package com.schoolmanagement.studentservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

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
}
