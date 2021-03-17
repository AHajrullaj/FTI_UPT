package com.example.demoUni.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.LENDEstudent;
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.entity.Mesazh;
import com.example.demoUni.entity.Sekretari;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.User;
import com.example.demoUni.service.CustomUserDetailsService;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.KerkesaService;
import com.example.demoUni.service.LendaService;
import com.example.demoUni.service.MesazhService;
import com.example.demoUni.service.SekretariService;
import com.example.demoUni.service.StudentService;
import com.example.demoUni.service.UserService;

@Controller
public class AccountantController {

	@Autowired DegaService degaService;
	@Autowired StudentService studentService;
	@Autowired LendaService lendaService;
	@Autowired CustomUserDetailsService userservice;
	@Autowired KerkesaService kerkesaService;
	@Autowired MesazhService mesazhService;
	@Autowired SekretariService sekretariService;
	@Autowired LendaService lendeService;
	@Autowired UserService userService;
	@RequestMapping("/acc-form")
	public ModelAndView showStudent() {
		 ModelAndView mav = new ModelAndView("acc-form");
		mav.addObject("Fillimi", 1);
		return mav;
	}
	
	@GetMapping("/acc-form/addAccList")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Sekretari sr=sekretariService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listSekretari", sr);
	    model.addAttribute("showSekretariList", 1);
	        return modelAndview;
	} 
	
	@RequestMapping(value ="/acc-form/listmybranches", method = RequestMethod.POST)
	public ModelAndView listmybranches(Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
	 
		List <Student>listStudente=studentService.listByDega(id);
	    model.addAttribute("listStudente", listStudente);
		model.addAttribute("listDege", degaService.listAll());
		model.addAttribute("showStudentList", 1);
        return modelAndview;
	}

	@GetMapping("/acc-form/addstudentlist")
	public ModelAndView redirectListBranches(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form"); 
	    model.addAttribute("listDege", degaService.listAll());
	    model.addAttribute("listStudente", studentService.listAll());
	    model.addAttribute("showStudentList", 1);
	        return modelAndview;
	}
	
	
	@RequestMapping(value ="/acc-form/listByLende", method = RequestMethod.POST)
	public ModelAndView listmybran (Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
	 
		List <Lenda>listLende=lendeService.FindByDega(id);
	    model.addAttribute("listLende",listLende);
		model.addAttribute("listDege", degaService.listAll());
		model.addAttribute("showLendeList", 1);
        return modelAndview;
	}

	@GetMapping("/acc-form/addLendetlist")
	public ModelAndView redirectLis(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form"); 
	    model.addAttribute("listDege", degaService.listAll());
	    model.addAttribute("listLende", lendeService.listAll());
	    model.addAttribute("showLendeList", 1);
	        return modelAndview;
	}
	
	//list kerkesa per sekretarine- te dergura
	@GetMapping("/acc-form/addkerkesaDerg")
	public ModelAndView  ListBranches(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form"); 
		
		List<Kerkesa> sc=new ArrayList();
		 
		for(Kerkesa c:kerkesaService.listAll()) {
			 if(c.isDerguar()) {
				 sc.add(c);
			 }
								
		}
		 
	    model.addAttribute("listKerkesa", sc);
	    model.addAttribute("showKerkesaDerg", 1);
	        return modelAndview;
	}
	
	//list kerkesa per sekretarine- te jo dergura
		@GetMapping("/acc-form/addkerkesapaDerg")
		public ModelAndView  provListBranches(Model model) {
			ModelAndView modelAndview = new ModelAndView("acc-form"); 
			
			List<Kerkesa> sc=new ArrayList();
			 
			for(Kerkesa c:kerkesaService.listAll()) {
				 if(!c.isDerguar()) {
					 sc.add(c);
				 }
									 
			}
			 
		    model.addAttribute("listKerkesa", sc);
		    model.addAttribute("showKerkesaJoDerg", 1);
		        return modelAndview;
		}
		//nga upload i njoftimi
		@RequestMapping("/return")
		public ModelAndView showback() {
			 ModelAndView mav = new ModelAndView("acc-form");
			mav.addObject("Fillimi", 1);
			return mav;
		}
		
		
		
		
		
		
		@GetMapping("/acc-form/addMesazh")
		  public ModelAndView redirectmesazhForm(Model model) {
			ModelAndView modelAndview = new ModelAndView("acc-form");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Sekretari st=sekretariService.findByEmail(auth.getName());
		    model.addAttribute("sekretari", st);
		   // model.addAttribute("users", userService.listAll()); 
			 Mesazh ms=new Mesazh();
			 model.addAttribute("mesazh", ms);
			model.addAttribute("showNewmesazhh", 1);
		      return modelAndview;
		}
		
		@GetMapping("/acc-form/addMesazhDerg")
		public ModelAndView indexOfStudents2(Model model) {
			ModelAndView modelAndview = new ModelAndView("acc-form");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Sekretari st=sekretariService.findByEmail(auth.getName());
		    //List<Student> listStudents = studentService.listAll();
		    
		    List<Mesazh> sc=new ArrayList();
			for(Mesazh c:mesazhService.listAll()) {
				if(c.getEmailD().equals(st.getEmail())) {
					sc.add(c);
					
				} 
			}
			  
			model.addAttribute("listMesazhe",sc);
			model.addAttribute("sekretari", st);
			 
			 
		    model.addAttribute("showMesDergg", 1);
		        return modelAndview;
		} 
		
		
		@GetMapping("/acc-form/addMesazhMarr")
		public ModelAndView indexOfStudents3(Model model) {
			ModelAndView modelAndview = new ModelAndView("acc-form");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Sekretari st=sekretariService.findByEmail(auth.getName());
		    //List<Student> listStudents = studentService.listAll();
		    List<Mesazh> sc=new ArrayList();
			for(Mesazh c:mesazhService.listAll()) {
				if(c.getEmailM().equals(st.getEmail())) {
					sc.add(c);
					
				} 
			}
			 
			model.addAttribute("listMesazhe",sc);
			model.addAttribute("sekretari", st);
			 
			 
		    model.addAttribute("showMesMarrr", 1);
		        return modelAndview;
		} 
		
		
		@RequestMapping("/Sekretari/view/{id}")
		public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		    ModelAndView mav = new ModelAndView("Sekretari/view_msg");
		    Mesazh msg = mesazhService.get(id);
		     
		    User s=new  User();
			for( User c:userService.listAll()) {
				if(c.getEmail().equals(msg.getEmailD())) {
					s=c;
					
				} 
			}
	 
		    mav.addObject("mesazh", msg);
		    mav.addObject("sekretari", s);  
		    return mav;
		}
		
		
		@RequestMapping("/Sekretari/viewM/{id}")
		public ModelAndView showEPage(@PathVariable(name = "id") int id) {
		    ModelAndView mav = new ModelAndView("Sekretari/view_msgM");
		    Mesazh msg = mesazhService.get(id);
		     
		    User s=new  User();
			for( User c:userService.listAll()) {
				if(c.getEmail().equals(msg.getEmailM())) {
					s=c;
					
				}  
			}
	 
		    mav.addObject("mesazh", msg);
		    mav.addObject("sekretari", s);  
		    return mav;
		}
 
}
