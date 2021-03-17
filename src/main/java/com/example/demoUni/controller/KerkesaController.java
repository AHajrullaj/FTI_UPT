package com.example.demoUni.controller;

import java.util.ArrayList;
import java.util.Arrays;
 
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.LENDEstudent;
import com.example.demoUni.entity.Student;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.KerkesaService;
import com.example.demoUni.service.StudentService; 
@Controller
public class KerkesaController {
	Student student ;
	Kerkesa kerkes;
  	@Autowired private KerkesaService kerkesaService;
  	@Autowired private StudentService studentService;
  	@Autowired private DegaService degaService;
  	
	@RequestMapping("/Kerkesa/new/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("Kerkesa/new_kerkese");
		
		student = studentService.get(id);
	    mav.addObject("dega", degaService.listAll()); 
	    mav.addObject("student", student);
	    
    	Kerkesa   kerkesa = new Kerkesa();
    	mav.addObject("kerkesa",  kerkesa);
	    List<String>  listkategori = Arrays.asList("vertetim studenti", "list notash");
	    mav.addObject("listkategori",  listkategori);
	   
	    
	    return mav;
	}
	
	@RequestMapping("/Kerkesa/index")
	public String showKerkesaList(Model model) {
		List<Kerkesa> listKerkesa =  kerkesaService.listAll();
	    model.addAttribute("listKerkesa", listKerkesa); 
	   // List<DBFile> sc=new ArrayList();
	    return "Kerkesa/index"; 
	} 
	
 
	 
	@RequestMapping(value = "/saveKerkesa", method = RequestMethod.POST)
	public String savePedagog(@ModelAttribute("kerkesa") Kerkesa kerkesa,@ModelAttribute("student") Student s1) {
		Kerkesa k =new Kerkesa();
		k.setDerguar(false);
		
		k.setDega(s1.getDega().getEmri());
		k.setStudent(s1);
		k.setKategori(kerkesa.getKategori());
		k.setFirstname(kerkesa.getFirstname());
		k.setLastname(kerkesa.getLastname());
		k.setEmail(kerkesa.getEmail());
		kerkesaService.save(k);
		 
		 
	    return "redirect:/student-form/addKerkesaList";
	}

	/*
	 * @RequestMapping(value = "/savedokumenti", method = RequestMethod.POST) public
	 * String savePed (@ModelAttribute("dbfile") DBFile file) {
	 * kerkes.setDerguar(true); kerkes.setFile(file); kerkesaService.save(kerkes);
	 * 
	 * 
	 * return "redirect:/Kerkesa/index"; }
	 */
	 
	 
	 

	@RequestMapping("/Kerkesa/delete/{id}")
	public String deleteKerkesa(@PathVariable(name = "id") int id) {
		kerkesaService.delete(id);
	    return "success";       
	}
}
