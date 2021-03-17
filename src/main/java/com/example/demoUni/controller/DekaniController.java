package com.example.demoUni.controller;

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

import com.example.demoUni.entity.Dekani;
import com.example.demoUni.entity.Role;
import com.example.demoUni.entity.User;
import com.example.demoUni.service.DekaniService;
import com.example.demoUni.service.RoleService;
import com.example.demoUni.service.UserService;

@Controller
public class DekaniController {
	
	@Autowired private RoleService roleService;
	@Autowired private UserService userService;
	@Autowired private DekaniService dekaniService;
	static User updtUser;
	
    @RequestMapping("/Dekani/new")
	public String showDekaniPage(Model model) {
	    Dekani  dekani = new Dekani();
	    model.addAttribute("dekani", dekani);
	    
	    
	    return "Dekani/new_dekani";
	}
	
	@RequestMapping("/Dekani/index")
	public String showDekaniList(Model model) {
	List<Dekani> listDekani = dekaniService.listAll();
    model.addAttribute("listDekani", listDekani); 
    return "Dekani/index"; 
	}
	
	 
	@RequestMapping(value = "/saveDekani", method = RequestMethod.POST)
	public String savedekani(@ModelAttribute("dekani") Dekani dekani ) {
		
		 
		dekaniService.save(dekani);
		
		User user=new User();
		user.setEmail(dekani.getEmail());
		user.setLastname(dekani.getLastname());
		user.setName(dekani.getFirstname());
		user.setPassword(dekani.getPass());
		Role role=roleService.getRole(1);
		Set<Role> roles=new HashSet<>();
		roles.add(role); 
		user.setRole(roles);
		user.setPassword(dekani.getPass());		
		userService.saveUser(user);
		
	     
	    return "redirect:/Dekani/index";
	}
	
	
	@RequestMapping(value="/saveDek", method = RequestMethod.POST)
	 public ModelAndView saveStudEdit(@ModelAttribute("dekani") Dekani dekani,ModelMap model) {
		System.out.println("---------HIII-------------"+ updtUser.getEmail());
		dekaniService.save(dekani);
		 updtUser.setPassword(dekani.getPass());
		 System.out.println("----------------------"+ updtUser.getPassword());
		 userService.saveUser(updtUser);
		 return new ModelAndView("forward:/dekani-form", model);
	 }
	 
	
	 
	 
	
	//myData Dekani-form.html
	@RequestMapping("/Dekani/edit/{id}")
	public ModelAndView showEditDekaiPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Dekani/edit_dekani");
	    Dekani  dekani = dekaniService.get(id);
	    mav.addObject("dekani", dekani); 
	    updtUser=userService.getUser(dekani.getEmail());   
		System.out.println("----------------------"+ updtUser.getEmail());
		
		 return mav;
	}
	
	 
	 
	
	
	/*
	 * @RequestMapping("/Dekani/edit/{id}") public ModelAndView
	 * showEditDekaniPage(@PathVariable(name = "id") int id) { ModelAndView mav =
	 * new ModelAndView("Dekani/edit_dekani"); Dekani dekani =
	 * dekaniService.get(id); mav.addObject("dekani", dekani);
	 * 
	 * 
	 * 
	 * return mav; }
	 */
	 

	@RequestMapping("/Dekani/delete/{id}")
	public String deleteDekani(@PathVariable(name = "id") int id) {
		dekaniService.delete(id);
		userService.delete(id);
	    return "success";       
	}  
}
