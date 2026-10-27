package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.InstructorRepository;
import com.example.demo.model.Instructor;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        super();
        this.instructorRepository = instructorRepository;
    }

    @PostMapping
    public ResponseEntity<Instructor> addInstructor(
            @RequestBody Instructor instructor) {

        Instructor savedInstructor = instructorRepository.save(instructor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedInstructor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> findByIdInstructor(
            @PathVariable("id") int id) {

        Instructor instructor = instructorRepository.findById(id).orElse(null);

        if (instructor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instructor);
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> findAll() {

        List<Instructor> list = instructorRepository.findAll();

        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructorById(
            @PathVariable("id") int id) {

        Instructor instructor = instructorRepository.findById(id).orElse(null);

        if (instructor == null) {
            return ResponseEntity.notFound().build();
        }

        instructorRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(
            @PathVariable("id") int id,
            @RequestBody Instructor instructor) {

        Instructor existingInstructor =
                instructorRepository.findById(id).orElse(null);

        if (existingInstructor == null) {
            return ResponseEntity.notFound().build();
        }

        instructor.setId(id);

        Instructor updatedInstructor =
                instructorRepository.save(instructor);

        return ResponseEntity.ok(updatedInstructor);
    }
}
