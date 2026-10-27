package com.example.demo.controller;

import java.time.LocalDate;
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

import com.example.demo.client.CourseClient;
import com.example.demo.client.InstructorClient;
import com.example.demo.dao.AssignmentRespository;
import com.example.demo.model.Assignment;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

	private final CourseClient courseClient;
	private final InstructorClient instructorClient;
	private final AssignmentRespository assignmentRespository;

	public AssignmentController(CourseClient courseClient, InstructorClient instructorClient,
			AssignmentRespository assignmentRespository) {
		super();
		this.courseClient = courseClient;
		this.instructorClient = instructorClient;
		this.assignmentRespository = assignmentRespository;
	}

	@PostMapping
	public ResponseEntity<?> addAssignment(@RequestBody Assignment assignment) {

		if (courseClient.getByIdCource(assignment.getCourseId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Course Not Found with Id => " + assignment.getCourseId());
		}

		if (instructorClient.findByIdInstructor(assignment.getInstructorId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Instructor Not Found with Id => " + assignment.getInstructorId());
		}

		assignment.setDuedate(LocalDate.now());
		assignmentRespository.save(assignment);

		return ResponseEntity.ok(assignment);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAssignmentById(@PathVariable("id") int id) {
		Assignment assignment = assignmentRespository.findById(id).orElse(null);

		if (assignment == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Assignment Not Found with Id => " + id);
		}
		return ResponseEntity.ok(assignment);
	}

	@GetMapping
	public ResponseEntity<List<Assignment>> findAll() {
		List<Assignment> list= assignmentRespository.findAll();
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAssignmentById(@PathVariable("id") int id) {
		Assignment assignment = assignmentRespository.findById(id).orElse(null);

		if (assignment == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Assignment Not Found with Id => " + id);
		}

		assignmentRespository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAssignment(@PathVariable("id") int id, @RequestBody Assignment assignment) {

		Assignment assignment2 = assignmentRespository.findById(id).orElse(null);
		if (assignment2 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Assignment Not Found with Id => " + id);
		}

		if (courseClient.getByIdCource(assignment.getCourseId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(" Assignment could not be updated because the Course was not found with the given ID => " + assignment.getCourseId());
		}

		if (instructorClient.findByIdInstructor(assignment.getInstructorId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(" Assignment could not be updated because the Instructor was not found with the given ID=> " + assignment.getInstructorId());
		}

		assignment.setId(id);
		assignmentRespository.save(assignment);

		return ResponseEntity.ok(assignment);
	}
}