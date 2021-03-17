 package com.example.demoUni.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.repository.DocRepository;
import com.example.demoUni.service.DocStorageService;
import com.example.demoUni.service.KerkesaService;

@Controller
public class DocController {
	
	
	public static Kerkesa kerkesa;
	@Autowired 
	private DocStorageService docStorageService;
	 @Autowired
	  private DocRepository docRepository;
	@Autowired 
	private KerkesaService kerkesaService;
	
	@RequestMapping("/doc/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) { 
		ModelAndView mav = new ModelAndView("doc");
		kerkesa= kerkesaService.get(id);
		mav.addObject("kerkesa",kerkesa);
		/*
		 * List<Doc> docs =docStorageService.getFiles(); model.addAttribute("docs",
		 * docs);
		 */
	   return mav;
	  }
	  
	  
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		 
		for (MultipartFile file: files) {
			docStorageService.saveFile(file);
			
		} 
		kerkesa.setFile(DocStorageService.doc);
		//kerkesa.setFile(docStorageService.getFile(DocStorageService.i));
		//kerkesa.setFile(docStorageService.getFile((int) (long) docRepository.count()));
		kerkesa.setDerguar(true);
		kerkesaService.save(kerkesa);
		return "redirect:/acc-form/addkerkesaDerg";
	}
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		Doc doc = docStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
}
