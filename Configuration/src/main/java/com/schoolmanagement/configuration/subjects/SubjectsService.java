package com.schoolmanagement.configuration.subjects;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectsService {

    private final SubjectsRepository subjectsRepository;

    public SubjectsService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    // Create or update a subject
    public SubjectsEntity saveSubject(SubjectsEntity subjectsEntity) {
        return subjectsRepository.save(subjectsEntity);
    }

    // Get all subjects
    public List<SubjectsEntity> getAllSubjects() {
        return subjectsRepository.findAll();
    }

    // Get a specific subject by ID
    public Optional<SubjectsEntity> getSubjectById(Long id) {
        return subjectsRepository.findById(id);
    }

    // Delete a subject by ID
    public void deleteSubject(Long id) {
        subjectsRepository.deleteById(id);
    }

    // Get all subjects related to a specific class
    public List<SubjectsEntity> getSubjectsByClass(Long classId) {
        return subjectsRepository.findAll(); // Modify as needed to filter by class
    }
}
