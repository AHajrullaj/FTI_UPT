package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoUni.entity.Dekani;
import com.example.demoUni.entity.Pedagog;
import com.example.demoUni.repository.PedagogRepository;
@Service

public class PedagogService {
 
 

	@Autowired PedagogRepository pedagogRepo;
	
	public List<Pedagog> listAll() {
        return (List<Pedagog>) pedagogRepo.findAll();
    }
	@Transactional 
	public void save(Pedagog pedagog) {
        pedagogRepo.save(pedagog);
    }
    
     
    public Pedagog get(long id) {
        return pedagogRepo.findById(id).get();
    }
     
    public void delete(long id) {
        pedagogRepo.deleteById(id);
    }
    public Pedagog findByEmail(String email) {
    	return pedagogRepo.findByEmail(email);
    }
 
}
