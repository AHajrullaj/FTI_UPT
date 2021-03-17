package com.example.demoUni.repository;

 
import org.springframework.data.repository.CrudRepository;

 
import com.example.demoUni.entity.Pedagog;

public interface PedagogRepository extends CrudRepository <Pedagog,Long>{
	 
	 
	public Pedagog findByEmail(String email);
}
