package com.schoolmanagement.configuration.classes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schoolmanagement.configuration.streams.StreamsEntity;
import com.schoolmanagement.configuration.subjects.SubjectsEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.Subject;
import java.util.List;

@Entity
@Getter
@Setter // Lombok will automatically generate getters and setters
public class ClassesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


}
