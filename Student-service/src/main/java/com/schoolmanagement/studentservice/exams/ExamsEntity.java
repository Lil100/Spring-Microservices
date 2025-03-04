package com.schoolmanagement.studentservice.exams;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schoolmanagement.studentservice.dto.GradingDTO;
import com.schoolmanagement.studentservice.student.StudentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExamsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private double score;
    private String term;

    @ManyToOne
//    @JsonBackReference
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @JsonIgnore
    @Transient  // Use @Transient if you don't want to persist GradingDTO
    private GradingDTO grading;



}
