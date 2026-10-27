package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.EnrollmentDto;


@FeignClient(name="enrollment-service",dismiss404 = true)
public interface EnrollementClient {
	
	@GetMapping ("/enrollments/{id}")
	public  EnrollmentDto findByIdEnrollment(@PathVariable("id") int id) ;
	

}
