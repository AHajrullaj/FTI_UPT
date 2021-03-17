package com.example.demoUni.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Dekani;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.Mesazh;
import com.example.demoUni.entity.Pedagog;
import com.example.demoUni.entity.Sekretari;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.User;
import com.example.demoUni.repository.UserRepository;
import com.example.demoUni.service.MesazhService;
import com.example.demoUni.service.StudentService;
import com.example.demoUni.service.UserService;

@Controller
public class MesazhController {
	Student student ;
	Mesazh m;
	User  optionalUser;
	@Autowired private MesazhService mesazhService;
	@Autowired private StudentService studentService;
	@Autowired private UserRepository userRepository;
	@Autowired private UserService userService;
	
	 @RequestMapping(value = "/autocomplete")
	    @ResponseBody
	    public List<String> autoName(){
		 
		 List<String> designation=new ArrayList();
			for(User c:userService.listAll()) {
				if(c.getEmail()==student.getEmail()) {
					designation.add(c.getEmail());
					
				}
			}
		 
		 
	       // List<String> designation = userService.listByEmail();
	        return designation;
	    }
	
	@RequestMapping("/Mesazh/new/{id}") 
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("Mesazh/new_mesazh");
		student = studentService.get(id);
		optionalUser=userRepository.findUserByEmail(student.getEmail());
		 mav.addObject("student", student);
		 mav.addObject("users", userService.listAll()); 
		 Mesazh ms=new Mesazh();
		 mav.addObject("mesazh", ms);
		return mav;
	}
	
	@RequestMapping("/Mesazh/sent/{id}")
	public ModelAndView showMesagederg(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("Mesazh/mesDergStudent");
		student = studentService.get(id);
		List<Mesazh> sc=new ArrayList();
		for(Mesazh c:mesazhService.listAll()) {
			if(c.getEmailD().equals(student.getEmail())) {
				sc.add(c);
				
			} 
		}
		System.out.println("ESHTE BOSHT ???      "+sc.isEmpty());
		mav.addObject("listMesazhe",sc);
		mav.addObject("student", student);
		 
		 
		return mav;
	}
	
	@RequestMapping("/Mesazh/recive/{id}")
	public ModelAndView showMesageMarr(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("Mesazh/mesMarrStudent");
		student = studentService.get(id);
		List<Mesazh> sc=new ArrayList();
		for(Mesazh c:mesazhService.listAll()) {
			if(c.getEmailM().equals(student.getEmail())) {
				sc.add(c);
				
			} 
		}
		System.out.println("ESHTE BOSHT ???      "+sc.isEmpty());
		mav.addObject("listMesazhe",sc);
		mav.addObject("student", student);
		 
		 
		return mav;
	}
	
	@RequestMapping(value = "/saveMesazh", method = RequestMethod.POST)
	public String savePedagog(@ModelAttribute("mesazh") Mesazh mesazh,@ModelAttribute("student") Student s1) {
		Mesazh m =new Mesazh();
		m.setEmailD(s1.getEmail());;
		m.setEmailM(mesazh.getEmailM());
		m.setTitle(mesazh.getTitle());
		m.setSubject(mesazh.getSubject());
		m.setDtDerg(mesazh.getDtDerg());
		m.setUser(userRepository.findUserByEmail(s1.getEmail()));
		 
		mesazhService.save(m);
		 
		 
	    return "redirect:/student-form";
	}
	//deg mgs sekretari
	@RequestMapping(value = "/saveMesazhSekretari", method = RequestMethod.POST)
	public String savePeds(@ModelAttribute("mesazh") Mesazh mesazh,@ModelAttribute("sekretari") Sekretari s1) {
		Mesazh m =new Mesazh();
		m.setEmailD(s1.getEmail());;
		m.setEmailM(mesazh.getEmailM());
		m.setTitle(mesazh.getTitle());
		m.setSubject(mesazh.getSubject());
		m.setDtDerg(mesazh.getDtDerg());
		m.setUser(userRepository.findUserByEmail(s1.getEmail()));
		 
		mesazhService.save(m);
		 
		 
	    return "redirect:/acc-form";
	}
	@RequestMapping(value = "/saveMesazhDekani", method = RequestMethod.POST)
	public String savePessds(@ModelAttribute("mesazh") Mesazh mesazh,@ModelAttribute("dekani") Dekani s1) {
		Mesazh m =new Mesazh();
		m.setEmailD(s1.getEmail());;
		m.setEmailM(mesazh.getEmailM());
		m.setTitle(mesazh.getTitle());
		m.setSubject(mesazh.getSubject());
		m.setDtDerg(mesazh.getDtDerg());
		m.setUser(userRepository.findUserByEmail(s1.getEmail()));
		 
		mesazhService.save(m);
		 
		 
	    return "redirect:/dekani-form";
	}
	@RequestMapping(value = "/saveMesazhPedagogu", method = RequestMethod.POST)
	public String savemesazhPedagogu(@ModelAttribute("mesazh") Mesazh mesazh,@ModelAttribute("pedagog") Pedagog s1) {
		Mesazh m =new Mesazh();
		m.setEmailD(s1.getEmail());;
		m.setEmailM(mesazh.getEmailM());
		m.setTitle(mesazh.getTitle());
		m.setSubject(mesazh.getSubject());
		m.setDtDerg(mesazh.getDtDerg());
		m.setUser(userRepository.findUserByEmail(s1.getEmail()));
		 
		mesazhService.save(m);
		 
		 
	    return "redirect:/pedagog-form";
	}
	 
}

