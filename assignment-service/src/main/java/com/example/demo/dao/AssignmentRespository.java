package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Assignment;

public interface AssignmentRespository extends JpaRepository<Assignment, Integer>{

}
