package com.schoolmanagement.configuration.streams;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.schoolmanagement.configuration.classes.ClassesEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter // Lombok will automatically generate getters and setters
public class StreamsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


}
