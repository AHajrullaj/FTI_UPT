package com.example.demoUni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
import com.example.demoUni.entity.Lenda;

public interface LendaRepository extends CrudRepository<Lenda,Long>{
 
	public List <Lenda> findByDegaId(long id);
}
