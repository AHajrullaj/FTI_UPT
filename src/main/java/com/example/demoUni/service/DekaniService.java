package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Dekani;
import com.example.demoUni.entity.Sekretari;
import com.example.demoUni.repository.DekaniRepository;
@Service
public class DekaniService {
 @Autowired DekaniRepository  dekaniRepo;
	
	public List<Dekani> listAll() {
        return (List<Dekani>) dekaniRepo.findAll();
    }
	 
	public void save(Dekani dekani) {
        dekaniRepo.save(dekani);
    }
    
     
    public Dekani get(long id) {
        return dekaniRepo.findById(id).get();
    }
     
    public void delete(long id) {
        dekaniRepo.deleteById(id);
    } 
    public Dekani findByEmail(String email) {
    	return dekaniRepo.findByEmail(email);
    }
}
