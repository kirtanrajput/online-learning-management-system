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

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentRepository studentRepository;
	
	     StudentController(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody  Student student) {
		
		 studentRepository.save(student);
		 return ResponseEntity.
				 status(HttpStatus.CREATED).body(student);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> findByIdStudent(@PathVariable("id") int id) {
		Student student =studentRepository.findById(id).orElse(null);
		if(student==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> findAllStudent(){
			List<Student> list=	studentRepository.findAll();
			
			return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  deleteByIdStudent(@PathVariable("id") int id) {
			Student student=studentRepository.findById(id).orElse(null);
			if(student==null) {
				return ResponseEntity.notFound().build();
			}
			studentRepository.deleteById(id);
			return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> eupdateStudent(@PathVariable("id") int id,@RequestBody Student student) {
	Student  student1=studentRepository.findById(id).orElse(null);
		if(student1==null) {
			return ResponseEntity.notFound().build();
		}
		student.setId(id);
		student = studentRepository.save(student);
		
		return ResponseEntity.ok(student);
	}
	
	
}
