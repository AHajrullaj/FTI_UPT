package com.example.demoUni.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
import org.springframework.stereotype.Repository;

 
import com.example.demoUni.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String username);
	
	User findUserByEmail(String email);
	
	

	
}
