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

    // Updated method to save student with class and stream information
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        // Fetch all classes (Forms) and streams from the configuration service
        List<ClassesDTO> allClasses = configurationServiceClient.getAllClasses();
        List<StreamsDTO> allStreams = configurationServiceClient.getAllStreams();

        // Ensure that the class and stream are valid by matching with the data from the configuration service
        Optional<ClassesDTO> classDto = allClasses.stream()
                .filter(c -> c.getName().equals(studentEntity.getClassName()))
                .findFirst();

        Optional<StreamsDTO> streamDto = allStreams.stream()
                .filter(s -> s.getName().equals(studentEntity.getStream()))
                .findFirst();

        // If class or stream is not valid, throw an exception or return a message
        if (classDto.isEmpty()) {
            throw new IllegalArgumentException("Invalid class name provided.");
        }

        if (streamDto.isEmpty()) {
            throw new IllegalArgumentException("Invalid stream name provided.");
        }

        // Set unique admission number
        studentEntity.setAdmissionNumber("ADM-" + UUID.randomUUID().toString().substring(0, 8));

        // Map the ClassesDTO and StreamsDTO to the student entity
        studentEntity.setClassName(classDto.get().getName()); // Assign class name
        studentEntity.setStream(streamDto.get().getName()); // Assign stream name

        // Save the student entity
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
