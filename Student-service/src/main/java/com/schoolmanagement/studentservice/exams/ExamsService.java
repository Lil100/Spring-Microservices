package com.schoolmanagement.studentservice.exams;

import com.schoolmanagement.studentservice.student.StudentEntity;
import com.schoolmanagement.studentservice.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
