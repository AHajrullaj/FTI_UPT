package com.example.demoUni.repository;

 

import org.springframework.data.repository.CrudRepository;

import com.example.demoUni.entity.Dekani;
import com.example.demoUni.entity.Sekretari;

public interface DekaniRepository extends CrudRepository <Dekani,Long>{
	 
	public Dekani findByEmail(String email);
	
}