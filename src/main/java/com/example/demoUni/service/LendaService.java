package com.example.demoUni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.repository.LendaRepository;

@Service
public class LendaService {

	@Autowired LendaRepository lendaRepository;
	
	public List<Lenda> listAll() {
        return (List<Lenda>) lendaRepository.findAll();
    }
	public List<Lenda> FindByDega(long id){
		return (List<Lenda>) lendaRepository.findByDegaId(id);
	}
	
	public void save(Lenda lenda) {
        lendaRepository.save(lenda);
    }
    
     
    public Lenda get(long id) {
        return lendaRepository.findById(id).get();
    }
     
    public void delete(long id) {
        lendaRepository.deleteById(id);
    }
}
