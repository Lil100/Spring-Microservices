package com.schoolmanagement.studentservice.student;

import com.schoolmanagement.studentservice.ConfigurationServiceClient;
import com.schoolmanagement.studentservice.dto.ClassesDTO;
import com.schoolmanagement.studentservice.dto.GradingDTO;
import com.schoolmanagement.studentservice.dto.StreamsDTO;
import com.schoolmanagement.studentservice.dto.SubjectsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

 @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ConfigurationServiceClient configurationServiceClient;

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        studentEntity.setAdmissionNumber("ADM-" + UUID.randomUUID().toString().substring(0, 8));
        return studentRepository.save(studentEntity);
    }

    public Optional<StudentEntity> getStudentByAdmissionNumber(String admissionNumber) {
        return studentRepository.findByAdmissionNumber(admissionNumber);
    }
    public List<ClassesDTO> getAllClasses() {
        return configurationServiceClient.getAllClasses();
    }

    public List<StreamsDTO> getAllStreams() {
        return configurationServiceClient.getAllStreams();
    }

    public List<SubjectsDTO> getAllSubjects() {
        return configurationServiceClient.getAllSubjects();
    }

    public List<GradingDTO> getAllGrades() {
        return configurationServiceClient.getAllGrades();
    }

}