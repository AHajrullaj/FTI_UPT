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
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.LendaService;
import com.example.demoUni.service.PedagogService;

@Controller
public class LendaController {
 
	@Autowired private LendaService lendaService;
	@Autowired private DegaService  degaService;
	@Autowired private PedagogService  pedagogService;
	@RequestMapping("/Lenda/index")
	public String showNewLendePage(Model model) {
		List<Lenda> listLende = lendaService.listAll();
	    model.addAttribute("listLende", listLende);
	   
	    return "Lenda/index";
	}
	
	@RequestMapping("/Lenda/new")
	public String showNewLendaPage(Model model) {
	    Lenda lenda = new Lenda();
	    model.addAttribute("lenda", lenda);
	    model.addAttribute("listDege", degaService.listAll()); 
	    
	    return "Lenda/new_lenda";
	}
	
	 
	@RequestMapping(value = "saveLenda", method = RequestMethod.POST)
	public String saveLenda(@ModelAttribute("lenda") Lenda lenda) {
		//System.out.println(dege.getFirstname()+"------------------------------------------"+student.dtRegjistrimi);
		lendaService.save(lenda);
	      
	    return "redirect:/dekani-form";
	} 
	@RequestMapping("/Lenda/edit/{id}")
	public ModelAndView showEditLendaPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Lenda/edit_lenda");
	    
	    Lenda lenda = lendaService.get(id);
	    mav.addObject("lenda", lenda);
	    mav.addObject("listDege", degaService.listAll());
	    
	    return mav;
	}
	
	@RequestMapping("/Lenda/delete/{id}")
	public String deleteLenda(@PathVariable(name = "id") int id) {
		lendaService.delete(id);
	    return "success";       
	}
}
