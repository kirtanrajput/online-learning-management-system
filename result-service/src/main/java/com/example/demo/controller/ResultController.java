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

import com.example.demo.client.AssignmentClient;
import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.ResultRepository;
import com.example.demo.model.Result;

@RestController
@RequestMapping("/results")
public class ResultController {

	private final ResultRepository resultRepository;
	private final StudentClient studentClient;
	private final EnrollmentClient enrollmentClient;
	private final AssignmentClient assignmentClient;

	public ResultController(ResultRepository resultRepository, StudentClient studentClient,
			EnrollmentClient enrollmentClient, AssignmentClient assignmentClient) {
		super();
		this.resultRepository = resultRepository;
		this.studentClient = studentClient;
		this.enrollmentClient = enrollmentClient;
		this.assignmentClient = assignmentClient;
	}

	@PostMapping
	public ResponseEntity<?> addResult(@RequestBody Result result) {

		if (studentClient.findByIdStudent(result.getStudentId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Student Not Found with Id => " + result.getStudentId());
		}

		if (enrollmentClient.findByIdEnrollment(result.getEnrollmentId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Enrollment Not Found with Id => " + result.getEnrollmentId());
		}

		if (assignmentClient.findAssignmentById(result.getAssignmentId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Assignment Not Found with Id => " + result.getAssignmentId());
		}

		result.setResultDate(LocalDate.now());
		resultRepository.save(result);

		return ResponseEntity.ok(result);
	}

	@GetMapping
	public ResponseEntity<List<Result>> findAll() {
		List<Result> list = resultRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		Result result = resultRepository.findById(id).orElse(null);

		if (result == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Result Not Found with Id => " + id);
		}
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		Result result = resultRepository.findById(id).orElse(null);

		if (result == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Result Not Found with Id => " + id);
		}

		resultRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateResult(@PathVariable("id") int id, @RequestBody Result result) {

		Result result2 = resultRepository.findById(id).orElse(null);
		if (result2 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Result Not Found with Id => " + id);
		}

		if (studentClient.findByIdStudent(result.getStudentId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Result could not be updated because the Student was not found with the given ID => "
							+ result.getStudentId());
		}

		if (enrollmentClient.findByIdEnrollment(result.getEnrollmentId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Result could not be updated because the Enrollment was not found with the given ID => "
							+ result.getEnrollmentId());
		}

		if (assignmentClient.findAssignmentById(result.getAssignmentId()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Result could not be updated because the Assignment was not found with the given ID => "
							+ result.getAssignmentId());
		}

		result.setId(id);
		result.setResultDate(LocalDate.now());
		resultRepository.save(result);

		return ResponseEntity.ok(result);
	}
}