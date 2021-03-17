package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.User;
import com.example.demoUni.repository.ForUserRepository;
import com.example.demoUni.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private ForUserRepository repo;
	
	public void saveUser(User user) {
		repo.save(user);
    }
	
	public User getUser(String email) {
		return repo.findByEmail(email);
		
	}
	
	 public void delete(int id) {
	        repo.deleteById(id);
	    }
	
	 public List<User> listAll() {
	        return (List<User>) repo.findAll();
	 }
	 
	

}
