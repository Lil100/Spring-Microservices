package com.schoolmanagement.configuration.classes;

import com.schoolmanagement.configuration.subjects.SubjectsEntity;
import com.schoolmanagement.configuration.streams.StreamsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClassController {

    @Autowired
    private ClassService classesService;

    // Endpoint to get all classes
    @GetMapping("/all")
    public List<ClassesEntity> getAllClasses() {
        return classesService.getAllClasses();
    }

    // Endpoint to get a class by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassesEntity> getClassById(@PathVariable Long id) {
        Optional<ClassesEntity> classEntity = classesService.getClassById(id);
        return classEntity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to create a new class
    @PostMapping("/add")
    public ResponseEntity<ClassesEntity> createClass(@RequestBody ClassesEntity classesEntity) {
        ClassesEntity createdClass = classesService.createClass(classesEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClass);
    }

    // Endpoint to update an existing class
    @PutMapping("/{id}")
    public ResponseEntity<ClassesEntity> updateClass(@PathVariable Long id, @RequestBody ClassesEntity classesEntity) {
        ClassesEntity updatedClass = classesService.updateClass(id, classesEntity);
        return updatedClass != null ? ResponseEntity.ok(updatedClass) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint to delete a class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

