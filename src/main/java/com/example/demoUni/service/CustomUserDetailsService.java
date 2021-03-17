package com.example.demoUni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.CustomUserDetails;
import com.example.demoUni.entity.User;
import com.example.demoUni.repository.UserRepository;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser=userRepository.findByEmail(username);
		
		optionalUser
		.orElseThrow(()->new UsernameNotFoundException("User not found"));
		
//		CustomUserDetails customUserDetails=optionalUser.map(user->{
//			return new CustomUserDetails(user);
//		}).get();
//		return customUserDetails;
		return optionalUser.map(CustomUserDetails::new).get();
	}

	public int getIdOfCurrentUser() {
		 Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		 String email = loggedInUser.getName(); 

		User optionalUser=userRepository.findUserByEmail(email);
		 
		    
		    return  optionalUser.getId();
	}
}
