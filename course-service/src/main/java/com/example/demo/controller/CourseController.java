package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CourseRepository;
import com.example.demo.model.Course;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseRepository courseRepository;

	public CourseController(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	@PostMapping
	public  ResponseEntity<Course> addCourse(@RequestBody Course course) {
			courseRepository.save(course);
			
			return ResponseEntity.
					 status(HttpStatus.SC_CREATED).body(course);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getByIdCource(@PathVariable("id") int id) {
		Course course =courseRepository.findById(id).orElse(null);
		if(course==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(course);
	}
	
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> list=courseRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> courseDeleteById(@PathVariable("id") int id) {
		Course course =courseRepository.findById(id).orElse(null);
		if(course==null) {
			return ResponseEntity.notFound().build();
		}
		courseRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") int id,@RequestBody Course course) {
		Course course1 =courseRepository.findById(id).orElse(null);
		if(course1==null) {
			return ResponseEntity.notFound().build();
		}
		course.setId(id);
		courseRepository.save(course);
	return ResponseEntity.status(HttpStatus.SC_CREATED).body(course);
	}
	
}
