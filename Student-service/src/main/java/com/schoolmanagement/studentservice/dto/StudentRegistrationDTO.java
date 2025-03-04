package com.schoolmanagement.studentservice.dto;

import com.schoolmanagement.studentservice.student.StudentEntity;

public class StudentRegistrationDTO {

    private StudentEntity studentEntity;
    private ClassesDTO classesDTO;
    private StreamsDTO streamsDTO;

    // Getters and Setters
    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public ClassesDTO getClassesDTO() {
        return classesDTO;
    }

    public void setClassesDTO(ClassesDTO classesDTO) {
        this.classesDTO = classesDTO;
    }

    public StreamsDTO getStreamsDTO() {
        return streamsDTO;
    }

    public void setStreamsDTO(StreamsDTO streamsDTO) {
        this.streamsDTO = streamsDTO;
    }
}
