package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int studentId;
	int courseId;
	LocalDate enrollmentdate;
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
	public LocalDate getEnrollmentdate() {
		return enrollmentdate;
	}
	public void setEnrollmentdate(LocalDate enrollmentdate) {
		this.enrollmentdate = enrollmentdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", enrollmentdate="
				+ enrollmentdate + ", status=" + status + "]";
	}
	
	
	
}
