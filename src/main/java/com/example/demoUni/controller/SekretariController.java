package com.example.demoUni.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.service.SekretariService;
import com.example.demoUni.entity.Sekretari;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.ImageGallery;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.Role;
import com.example.demoUni.entity.User;
import com.example.demoUni.service.RoleService;
import com.example.demoUni.service.UserService;

@Controller
public class SekretariController {
	@Autowired private RoleService roleService;
	@Autowired private UserService userService;
	@Autowired private SekretariService sekretariService;
	static User updtUser;
	
	
	 @RequestMapping("/Sekretari/new")
		public ModelAndView showSekretariPage(Model model) {
		 ModelAndView modelAndview = new ModelAndView("dekani-form");
		 Sekretari  sekretari = new Sekretari();
		    model.addAttribute("sekretari", sekretari);
		    model.addAttribute("showCreateNewsekretare", 1); 
		    
		    return modelAndview;
		}
		
		@RequestMapping("/Sekretari/index")
		public ModelAndView showSekretariList(Model model) {
			ModelAndView modelAndview = new ModelAndView("dekani-form");
			List<Sekretari> listSekretari = sekretariService.listAll();
			model.addAttribute("listSekretari", listSekretari); 
	    	model.addAttribute("showlistSekretare", 1); 
	    	return modelAndview;
		}
		
		 
		@RequestMapping(value = "/saveSekretari", method = RequestMethod.POST)
		public String savedekani(@ModelAttribute("sekretari") Sekretari sekretari ) {
			
			 
			sekretariService.save(sekretari);
			
			User user=new User();
			user.setEmail(sekretari.getEmail());
			user.setLastname(sekretari.getLastname());
			user.setName(sekretari.getFirstname());
			user.setPassword(sekretari.getPass());
			Role role=roleService.getRole(2);
			Set<Role> roles=new HashSet<>();
			roles.add(role); 
			user.setRole(roles);
			user.setPassword(sekretari.getPass());		
			userService.saveUser(user);
			
		     
		    return "redirect:/Sekretari/index";
		}
		
		
		
		@RequestMapping(value = "/saveSek", method = RequestMethod.POST)
		 public ModelAndView saveStudentEdit(@ModelAttribute("sekretari") Sekretari sekretari,ModelMap model) {
			sekretariService.save(sekretari);
			 updtUser.setPassword(sekretari.getPass());
			 userService.saveUser(updtUser);
			 return new ModelAndView("forward:/acc-form", model);
		 }
		 
		
		
		 
		 
		
		
		@RequestMapping("/Sekretari/edit/{id}")
		public ModelAndView showEditDekaniPage(@PathVariable(name = "id") int id) {
		    ModelAndView mav = new ModelAndView("Sekretari/edit_sekretari");
		    Sekretari sekretari = sekretariService.get(id);
		    mav.addObject("sekretari", sekretari); 
		    updtUser=userService.getUser(sekretari.getEmail());  
			
			
			 return mav;
		}
		
		@RequestMapping("/Sekretari/editdekani/{id}")
		public ModelAndView showEditDekane(@PathVariable(name = "id") int id) {
		    ModelAndView mav = new ModelAndView("Dekani/edit_sekretari");
		    Sekretari sekretari = sekretariService.get(id);
		    mav.addObject("sekretari", sekretari); 
		    updtUser=userService.getUser(sekretari.getEmail());  
			
			
			 return mav;
		}
		
		 

		@RequestMapping("/Sekretari/delete/{id}")
		public String deleteSekretari(@PathVariable(name = "id") int id) {
			sekretariService.delete(id);
			userService.delete(id);
		    return "success";       
		}  
}
