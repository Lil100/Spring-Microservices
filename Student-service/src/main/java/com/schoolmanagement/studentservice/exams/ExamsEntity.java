package com.schoolmanagement.studentservice.exams;

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
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Transient
    private Long studentId;

}
