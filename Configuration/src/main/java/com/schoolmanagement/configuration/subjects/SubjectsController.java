package com.schoolmanagement.configuration.subjects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    // Endpoint to create or update a subject
    @PostMapping("/add")
    public ResponseEntity<SubjectsEntity> createOrUpdateSubject(@RequestBody SubjectsEntity subjectsEntity) {
        SubjectsEntity savedSubject = subjectsService.saveSubject(subjectsEntity);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    // Endpoint to get all subjects
    @GetMapping("/all")
    public ResponseEntity<List<SubjectsEntity>> getAllSubjects() {
        List<SubjectsEntity> subjects = subjectsService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    // Endpoint to get a specific subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectsEntity> getSubjectById(@PathVariable Long id) {
        Optional<SubjectsEntity> subject = subjectsService.getSubjectById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to delete a subject by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectsService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get subjects for a specific class (You might need to customize this further)
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<SubjectsEntity>> getSubjectsByClass(@PathVariable Long classId) {
        List<SubjectsEntity> subjects = subjectsService.getSubjectsByClass(classId);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
