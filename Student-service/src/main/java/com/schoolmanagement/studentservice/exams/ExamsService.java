package com.schoolmanagement.studentservice.exams;

import com.schoolmanagement.studentservice.AuthenticationServiceClient;
import com.schoolmanagement.studentservice.dto.UserTokenResponseDTO;
import com.schoolmanagement.studentservice.student.StudentEntity;
import com.schoolmanagement.studentservice.student.StudentRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ExamsService {

    @Autowired
    private ExamsRepository examsRepository;

    @Autowired
    private StudentRepository studentRepository;

    public ExamsEntity saveExam(ExamsEntity examsEntity) {
        // Check if studentId is provided
        if (examsEntity.getStudentId() != null) {
            Optional<StudentEntity> studentOptional = studentRepository.findById(examsEntity.getStudentId());
            if (studentOptional.isPresent()) {
                examsEntity.setStudent(studentOptional.get()); // Associate student with exam
            } else {
                throw new RuntimeException("Student not found");
            }
        }

        return examsRepository.save(examsEntity); // Save the exam with the associated student
    }

    public List<ExamsEntity> getExamsByStudent(Long studentId) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);
        return student.map(examsRepository::findByStudent).orElseThrow(() -> new RuntimeException("Student not found"));
    }
    @Autowired
    private AuthenticationServiceClient authenticationServiceClient;

    public void performActionWithValidation(String token) {
        try {
            ResponseEntity<UserTokenResponseDTO> response = authenticationServiceClient.validateToken("Bearer " + token);

            if (response.getStatusCode() == HttpStatus.OK) {
                // The token is valid. Proceed with the action (e.g., CRUD operations)
                UserTokenResponseDTO user = response.getBody();
                // Perform operations using the validated user details
            } else {
                // Handle invalid token scenario (unauthorized access)
                throw new RuntimeException("Invalid token");
            }
        } catch (FeignException ex) {
            // Handle Feign exception (e.g., authentication service is down)
            throw new RuntimeException("Error validating token", ex);
        }
    }
}

