package com.example.demoUni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Role;
import com.example.demoUni.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repo;
	
	 public Role getRole(int id) {
		 return repo.findById(id).get();
    }

}
