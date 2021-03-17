package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Sekretari;
 
import com.example.demoUni.repository.SekretariRepository;

@Service
public class SekretariService {
	 @Autowired SekretariRepository  sekretariRepo;
	 public List<Sekretari> listAll() {
	        return (List<Sekretari>)sekretariRepo.findAll();
	    }
		 
		public void save(Sekretari sekretari) {
			sekretariRepo.save(sekretari);
	    }
	    
	     
	    public Sekretari get(long id) {
	        return sekretariRepo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	    	sekretariRepo.deleteById(id);
	    } 
	    public Sekretari findByEmail(String email) {
	    	return sekretariRepo.findByEmail(email);
	    }
}
