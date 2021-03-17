package com.example.demoUni.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demoUni.entity.Sekretari;
import com.example.demoUni.entity.Student;

public interface SekretariRepository extends CrudRepository <Sekretari,Long>{
	public Sekretari findByEmail(String email);
}
