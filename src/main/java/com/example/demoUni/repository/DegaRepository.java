package com.example.demoUni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demoUni.entity.Dega;



@Repository
public interface DegaRepository extends CrudRepository<Dega, Long> {
	 //public List<Branch> findByFacultyId(long id);
}
