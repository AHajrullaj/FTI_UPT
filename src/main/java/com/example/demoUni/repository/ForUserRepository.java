package com.example.demoUni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoUni.entity.User;

@Repository
public interface ForUserRepository extends JpaRepository<User,Integer>{
	User findByEmail(String email);
	

}
