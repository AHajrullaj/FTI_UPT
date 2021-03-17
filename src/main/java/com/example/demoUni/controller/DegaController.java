package com.example.demoUni.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Dega;
import com.example.demoUni.service.DegaService;

@Controller
public class DegaController {

@Autowired private DegaService degaService;
	
	 
	
	@RequestMapping("/Dega/index")
	public String showNewDegePage(Model model) {
		List<Dega> listDege = degaService.listAll();
	    model.addAttribute("listDege", listDege);
	    return "Dega/index";
	}
	
	@RequestMapping("/Dega/new")
	public String showNewStudentPage(Model model) {
	    Dega dega = new Dega();
	    model.addAttribute("dega", dega);
		/*
		 * List<String> listprofile = Arrays.asList("Bachelor", "Master Profesional",
		 * "Master i Shkencave"); model.addAttribute("listprofile", listprofile);
		 */
	    return "Dega/new_dega";
	}
	
	//another handler method to save  the product information into the database:
	@RequestMapping(value = "saveDege", method = RequestMethod.POST)
	public String saveDege(@ModelAttribute("dega") Dega dega) {
		//System.out.println(dege.getFirstname()+"------------------------------------------"+student.dtRegjistrimi);
		degaService.save(dega);
	      
	    return "redirect:/dekani-form";
	} 
	@RequestMapping("/Dega/edit/{id}")
	public ModelAndView showEditDegaPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Dekani/edit_dega");
	    Dega dega = degaService.get(id);
	    mav.addObject("dega", dega);
		/*
		 * List<String> listprofile = Arrays.asList("Bachelor",
		 * "Master Profesional","Master i Shkencave");
		 * mav.addObject("listprofile",listprofile);
		 */
	    return mav;
	}
	
	@RequestMapping("/Dega/delete/{id}")
	public String deleteDega(@PathVariable(name = "id") int id) {
		degaService.delete(id);
	    return "success";       
	}
}
