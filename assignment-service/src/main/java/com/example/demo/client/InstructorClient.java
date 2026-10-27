package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.InstructorDto;


@FeignClient(name="instructor-service",dismiss404 = true)
public interface InstructorClient {
	@GetMapping("/instructors/{id}")
	public InstructorDto findByIdInstructor(@PathVariable("id") int id);
}
