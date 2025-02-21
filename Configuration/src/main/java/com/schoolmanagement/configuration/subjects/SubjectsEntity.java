package com.schoolmanagement.configuration.subjects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.schoolmanagement.configuration.classes.ClassesEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class SubjectsEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;



}
