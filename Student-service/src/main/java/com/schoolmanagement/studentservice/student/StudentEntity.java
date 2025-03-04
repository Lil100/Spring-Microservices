package com.schoolmanagement.studentservice.student;

import com.schoolmanagement.studentservice.dto.ClassesDTO;
import com.schoolmanagement.studentservice.dto.StreamsDTO;
import com.schoolmanagement.studentservice.dto.SubjectsDTO;
import com.schoolmanagement.studentservice.exams.ExamsEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String admissionNumber;

    private String address;
    private String className; // Storing class name as a String (from DTO)

    private String stream;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;


}
