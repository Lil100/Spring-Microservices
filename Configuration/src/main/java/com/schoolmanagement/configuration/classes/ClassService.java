package com.schoolmanagement.configuration.classes;

import com.schoolmanagement.configuration.subjects.SubjectsEntity;
import com.schoolmanagement.configuration.streams.StreamsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classesRepository;

    // Method to get all classes
    public List<ClassesEntity> getAllClasses() {
        return classesRepository.findAll();
    }

    // Method to get a class by ID
    public Optional<ClassesEntity> getClassById(Long id) {
        return classesRepository.findById(id);
    }

    // Method to create a new class
    public ClassesEntity createClass(ClassesEntity classesEntity) {
        return classesRepository.save(classesEntity);
    }

    // Method to update an existing class
    public ClassesEntity updateClass(Long id, ClassesEntity classesEntity) {
        if (classesRepository.existsById(id)) {
            classesEntity.setId(id);
            return classesRepository.save(classesEntity);
        }
        return null; // Or throw an exception if not found
    }

    // Method to delete a class by ID
    public void deleteClass(Long id) {
        if (classesRepository.existsById(id)) {
            classesRepository.deleteById(id);
        }
    }
}

