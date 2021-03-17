package com.example.demoUni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demoUni.entity.Student;

public interface StudentRepository extends CrudRepository<Student,Long>{
	
	 public List<Student> findByDegaId(long id);
	 public List<Student> findByLendStudent(long id);
	 public Student findByEmail(String email);
	 
}