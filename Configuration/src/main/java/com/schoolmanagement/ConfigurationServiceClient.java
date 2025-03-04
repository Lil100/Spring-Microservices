package com.schoolmanagement;

import com.schoolmanagement.configuration.classes.ClassesEntity;
import com.schoolmanagement.configuration.grading.GradingEntity;
import com.schoolmanagement.configuration.streams.StreamsEntity;
import com.schoolmanagement.configuration.subjects.SubjectsEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ConfigurationServiceClient {
    @GetMapping("/classes")
    List<ClassesEntity> getAllClasses();

    @GetMapping("/streams")
    List<StreamsEntity> getAllStreams();

    @GetMapping("/subjects")
    List<SubjectsEntity> getAllSubjects();

    @GetMapping("/grades")
    List<GradingEntity> getAllGrades();

    @GetMapping("/api/subjects/byIds")
    List<SubjectsEntity> getSubjectsByIds(@RequestParam List<String> ids);


}
