package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CourseDto;


@FeignClient(name="course-service",dismiss404 = true)
public interface CourseClient {


	@GetMapping("/courses/{id}")
	public CourseDto getByIdCource(@PathVariable("id") int id);
}
