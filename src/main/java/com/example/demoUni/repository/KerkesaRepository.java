package com.example.demoUni.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.Kerkesa;

public interface KerkesaRepository extends CrudRepository<Kerkesa,Long>{
	
	  @Modifying(clearAutomatically = true)
	  @Transactional
	  @Query("update Kerkesa k set k.file = ?1 where k.id = ?2") 
	  void setIdDokumentiForKerkesa(Doc idDOK, Long id);
	 
}
