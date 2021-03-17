package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.Student;
import com.example.demoUni.repository.DegaRepository;



@Service
public class DegaService {

	@Autowired DegaRepository degarepo;
	
	public List<Dega> listAll() {
        return (List<Dega>) degarepo.findAll();
    }
	
	public void save(Dega dega) {
        degarepo.save(dega);
    }
    
     
    public Dega get(long id) {
        return degarepo.findById(id).get();
    }
     
    public void delete(long id) {
        degarepo.deleteById(id);
    }
}
