package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String title;
	String description;
	int courseId;
	int instructorId;
	LocalDate duedate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public LocalDate getDuedate() {
		return duedate;
	}
	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}
	
	
}
