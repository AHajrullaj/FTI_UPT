package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Mesazh;
import com.example.demoUni.repository.MesazhRepository;

@Service
public class MesazhService {

	 @Autowired MesazhRepository mesazhRepository;
	 
	 public List<Mesazh> listAll() {
	        return (List<Mesazh>) mesazhRepository.findAll();
	    }
		 
		public void save(Mesazh mesazh) {
			mesazhRepository.save(mesazh);
	    }
	    
	     
	    public Mesazh get(long id) {
	        return mesazhRepository.findById(id).get();
	    }
	     
	    public void delete(long id) {
	    	mesazhRepository.deleteById(id);
	    } 
}
