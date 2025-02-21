package com.schoolmanagement.studentservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

 @Autowired
    private StudentRepository studentRepository;

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        studentEntity.setAdmissionNumber("ADM-" + UUID.randomUUID().toString().substring(0, 8));
        return studentRepository.save(studentEntity);
    }

    public Optional<StudentEntity> getStudentByAdmissionNumber(String admissionNumber) {
        return studentRepository.findByAdmissionNumber(admissionNumber);
    }
}