package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Student;
import com.example.demoUni.repository.StudentRepository;

@Service
public class StudentService {
	   @Autowired
	    private StudentRepository repo;
	    public List<Student> listAll() {
	        return (List<Student>) repo.findAll();
	    }
	    
	
	  public List<Student> listByDega(long id){
		  return (List<Student>)repo.findByDegaId(id);
	  }
	  
	  public List<Student> listBynotat(long id){
		  return (List<Student>)repo.findByLendStudent(id);
	  }
	  
	    
	    public void save(Student student) {
	        repo.save(student);
	    }
	    
	     
	    public Student get(long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	        repo.deleteById(id);
	    }
	    public Student findByEmail(String email) {
	    	return repo.findByEmail(email);
	    }
}