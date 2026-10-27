package com.example.demo.dto;

import java.time.LocalDate;

public class Enrollment {
	int id;
	int studentId;
	int courseId;
	LocalDate enrollmentdata;
	String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public LocalDate getEnrollmentdata() {
		return enrollmentdata;
	}
	public void setEnrollmentdata(LocalDate enrollmentdata) {
		this.enrollmentdata = enrollmentdata;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
