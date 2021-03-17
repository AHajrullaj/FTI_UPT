package com.example.demoUni.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.repository.KerkesaRepository;
@Service
public class KerkesaService {
	@Autowired KerkesaRepository kerkesarepo;
	
	public List<Kerkesa> listAll() {
        return (List<Kerkesa>) kerkesarepo.findAll();
    }
	
	public void save(Kerkesa dega) {
		kerkesarepo.save(dega);
    }
    
     
    public Kerkesa get(long id) {
        return kerkesarepo.findById(id).get();
    }
     
    public void delete(long id) {
    	kerkesarepo.deleteById(id);
    }
    @Transactional
    public void updateDoc(Doc dokid, long id) {
    	kerkesarepo.setIdDokumentiForKerkesa(dokid, id);
    }
}
