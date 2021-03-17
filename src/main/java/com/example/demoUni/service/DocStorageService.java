package com.example.demoUni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demoUni.service.KerkesaService;
import com.example.demoUni.controller.DocController;
import com.example.demoUni.entity.Doc;
import com.example.demoUni.repository.DocRepository;
import com.example.demoUni.repository.KerkesaRepository;

@Service
public class DocStorageService {
	public static Doc doc;
  @Autowired
  private DocRepository docRepository;
  @Autowired
  private  KerkesaRepository  kerkesaRepository;
  @Autowired 
	private KerkesaService kerkesaService;
  
  public void saveFile(MultipartFile file) {
	  String docname = file.getOriginalFilename();
	  try {
		  doc = new Doc(docname,file.getContentType(),file.getBytes());
		  if(file.getSize()>4194304) {
			  System.out.print("Permas e madhe");
		  }else {
			  docRepository.save(doc); 
		  }
		 
		 // kerkesaService.updateDoc(doc, DocController.kerkesa.getId());
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  } 
	  
  }
  public Optional<Doc> getFile(Integer l) {
	  return docRepository.findById(l);
  }
  public List<Doc> getFiles(){
	  return docRepository.findAll();
  }
}
