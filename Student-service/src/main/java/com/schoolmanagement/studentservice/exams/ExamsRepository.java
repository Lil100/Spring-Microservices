package com.schoolmanagement.studentservice.exams;

import com.schoolmanagement.studentservice.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExamsRepository extends JpaRepository<ExamsEntity, Long> {
    List<ExamsEntity> findByStudent(StudentEntity student);
}