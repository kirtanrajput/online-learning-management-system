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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.EnrollementClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.model.Payment;


@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentRepository paymentRepository;
	
	private final StudentClient studentClient;
	
	private final EnrollementClient enrollementClient;

	public PaymentController(PaymentRepository paymentRepository, StudentClient studentClient,
			EnrollementClient enrollementClient) {
		super();
		this.paymentRepository = paymentRepository;
		this.studentClient = studentClient;
		this.enrollementClient = enrollementClient;
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
		
		
		if(enrollementClient.findByIdEnrollment(payment.getEnrollmentid())==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Course Not Found with Id => "+payment.getEnrollmentid());
		}
		
		if(studentClient.findByIdStudent(payment.getStudentId())==null) {
			return  ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Student Not Found with Id => "+payment.getStudentId());
		}
		
		payment.setPaymentDate(LocalDate.now());
		
		paymentRepository.save(payment);
		
		return ResponseEntity.ok(payment);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByIdPayment(@PathVariable("id") int id) {
		Payment payment= paymentRepository.findById(id).orElse(null);
		if(payment==null) {

			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Payment Not Found with Id => "+ id);
		}
		return ResponseEntity.ok(payment);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Payment>> findAll(){
		List<Payment> list =paymentRepository.findAll();
		
		return ResponseEntity.ok(list);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteByIdPayment(@PathVariable("id") int id) {
		Payment payment=paymentRepository.findById(id).orElse(null);
		
		if(payment==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Payment Not Found with Id => "+ id);
		}
		paymentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>  updatePayment(@PathVariable("id") int id,@RequestBody Payment payment) {
		
		Payment payment2=paymentRepository.findById(id).orElse(null);
		if(payment2==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Payment Not Found with Id => "+ id);
		}
		
		if(enrollementClient.findByIdEnrollment(payment.getEnrollmentid())==null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Payment could not be updated because the Enrollment was not found with the given ID => "+payment.getEnrollmentid());
		}
		
		if(studentClient.findByIdStudent(payment.getStudentId())==null) {
			return  ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Payment could not be updated because the Student was not found with the given ID => "+payment.getStudentId());
		}

		payment.setPaymentDate(LocalDate.now());
		payment.setId(id);
		paymentRepository.save(payment);
		
		return ResponseEntity.ok(payment);
	}
	
}
