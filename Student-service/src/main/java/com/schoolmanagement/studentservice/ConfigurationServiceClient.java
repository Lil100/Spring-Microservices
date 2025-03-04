package com.schoolmanagement.studentservice;

import com.schoolmanagement.studentservice.dto.ClassesDTO;
import com.schoolmanagement.studentservice.dto.GradingDTO;
import com.schoolmanagement.studentservice.dto.StreamsDTO;
import com.schoolmanagement.studentservice.dto.SubjectsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "configuration", url = "http://localhost:8081")
public interface ConfigurationServiceClient {

    @GetMapping("/api/classes/all")
    List<ClassesDTO> getAllClasses();

    @GetMapping("/api/streams/all")
    List<StreamsDTO> getAllStreams();

    @GetMapping("/api/subjects/all")
    List<SubjectsDTO> getAllSubjects();

    @GetMapping("/api/grading/all")
    List<GradingDTO> getAllGrades();


}
