package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.client.CourseClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.EnrollmentRepository;
import com.example.demo.model.Enrollment;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	private final EnrollmentRepository enrollmentRepository;
	
	private final StudentClient studentClient;
	private final CourseClient courseClient;

	
	
	
	public EnrollmentController(EnrollmentRepository enrollmentRepository, StudentClient studentClient,
			CourseClient courseClient) {
		super();
		this.enrollmentRepository = enrollmentRepository;
		this.studentClient = studentClient;
		this.courseClient = courseClient;
	}




	@PostMapping
	public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enrollment) {
		
		
		if(courseClient.getByIdCource(enrollment.getCourseId())==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Course Not Found with Id => "+enrollment.getCourseId());
		}
		
		if(studentClient.findByIdStudent(enrollment.getStudentId())==null) {
			return  ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Student Not Found with Id => "+enrollment.getStudentId());
		}
		
		enrollment.setEnrollmentdate(LocalDate.now());
		
		enrollmentRepository.save(enrollment);
		
		return ResponseEntity.ok(enrollment);
	}
	


	@GetMapping ("/{id}")
	public  ResponseEntity<?> findByIdEnrollment(@PathVariable("id") int id) {
		
		Enrollment enrollment=enrollmentRepository.findById(id).orElse(null);
		if(enrollment==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Enrollment Not Found with Id => "+ id);
		}
		return ResponseEntity.ok(enrollment);
	}
	
	@GetMapping
	public ResponseEntity<List<Enrollment>> findAll(){
		List<Enrollment> list= enrollmentRepository.findAll();
		
		return ResponseEntity.ok(list);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		
		Enrollment enrollment=enrollmentRepository.findById(id).orElse(null);
		if(enrollment==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Enrollment Not Found with Id => "+ id);
		}
		
		enrollmentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody Enrollment enrollment) {
		
		Enrollment enrollment2=enrollmentRepository.findById(id).orElse(null);
		
		if(enrollment2==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Enrollment Not Found with Id => "+ id);
		}

		if(courseClient.getByIdCource(enrollment.getCourseId())==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Enrollment could not be updated because the course was not found with the given ID. => "+enrollment.getCourseId());
		}
		
		if(studentClient.findByIdStudent(enrollment.getStudentId())==null) {
			return  ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Enrollment could not be updated because the student was not found with the given ID.=> "+enrollment.getStudentId());
		}
		
		enrollment.setEnrollmentdate(LocalDate.now());
		enrollment.setId(id);
		enrollmentRepository.save(enrollment);
		return ResponseEntity.ok(enrollment);
	}
	
}
