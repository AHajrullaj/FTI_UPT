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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.LENDEstudent;
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.entity.Role;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.User;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.DocStorageService;
import com.example.demoUni.service.KerkesaService;
import com.example.demoUni.service.LendaService;
import com.example.demoUni.service.RoleService;
import com.example.demoUni.service.StudentService;
import com.example.demoUni.service.UserService;

@Controller
public class StudentController {
	static User updtUser;
	static Dega d;
	@Autowired private StudentService studentService;
	@Autowired private LendaService lendaService;
	@Autowired private DegaService degaService;
	@Autowired private RoleService roleService;
	@Autowired private UserService userService;
	@Autowired private KerkesaService kerkesaService;
	@Autowired private DocStorageService docService;
	 @RequestMapping("/") 
	 public String viewHomePage(Model model) {
		 return "index2";
	 }
	 
	//acc-form shfaq modulin qe te krijosh nje student
	@RequestMapping("/acc-form/addNewStudent")
	public ModelAndView showNewStudentPage(Model model) {
		ModelAndView modelAndview = new ModelAndView("acc-form");
	    Student student = new Student();
	    model.addAttribute("student", student);
	    model.addAttribute("listDege", degaService.listAll());
	    model.addAttribute("showCreateNewStudent", 1);
	    return modelAndview;
	}
	
	@RequestMapping("/Student/index")
	public String showStudentList(Model model) {
	List<Student> listStudents = studentService.listAll();
    model.addAttribute("listStudents", listStudents);
    model.addAttribute("listlende", lendaService);
    return "Student/index"; 
	}
	
	//acc-form krijo sekretaria studentet
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("student") Student student,@RequestParam(value="dega") long id) {
		
		Dega dega =degaService.get(id);
		 
		student.setDega(dega);
		List<LENDEstudent> sc=new ArrayList();
		 
		for(Lenda c:lendaService.FindByDega(dega.getId())) {
			LENDEstudent studentCourses=new LENDEstudent();				
				studentCourses.setStudent(student);
				studentCourses.setLenda(c);
			    studentCourses.setNota("N/A");
				 
				
				sc.add(studentCourses);
								
		}
		
		student.setLendStudent(sc);
		
		
		
		studentService.save(student);
		
		User user=new User();
		user.setEmail(student.getEmail());
		user.setLastname(student.getLastname());
		user.setName(student.getFirstname());
		user.setPassword(student.getPass());
		Role role=roleService.getRole(4);
		Set<Role> roles=new HashSet<>();
		roles.add(role); 
		user.setRole(roles);
		user.setPassword(student.getPass());		
		userService.saveUser(user);
		
	     
	    return "redirect:/acc-form";
	}
	
	
	
	
	
	 @RequestMapping(value = "/saveE", method = RequestMethod.POST)
	 public ModelAndView saveStudentEdit(@ModelAttribute("student") Student student, ModelMap model) {
		  
	     student.setDega(d);
		 studentService.save(student);
		  
		 updtUser.setPassword(student.getPass());
		 userService.saveUser(updtUser);
		 return new ModelAndView("forward:/student-form", model);
	 }
	 
	
	
	@RequestMapping("/Student/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Student/edit_student");
	    Student student = studentService.get(id);
	    d=student.getDega();
	    mav.addObject("dega", degaService.listAll()); 
		mav.addObject("lendet",lendaService.FindByDega( student.getDega().getId()));
		
		
		List<Kerkesa> sc=new ArrayList();
		List<Doc> dc=new ArrayList(); 
		for(Kerkesa c:kerkesaService.listAll()) {
			if(c.getStudent().getId()==student.getId()) {
				sc.add(c);
				dc.add(c.getFile());
			}
		}
		
		 updtUser=userService.getUser(student.getEmail()); 
		mav.addObject("docs",dc); 
		mav.addObject("listKerkesash",sc);
		 
 
	    mav.addObject("student", student);
	      
	    return mav;
	}
	
	//edit nga sekretaria
	@RequestMapping("/Student/editt/{id}")
	public ModelAndView showEditProductPe(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Sekretari/edit_student");
	    Student student = studentService.get(id);
	    mav.addObject("dega", degaService.listAll()); 
		mav.addObject("lendet",lendaService.FindByDega( student.getDega().getId()));
		
		
		List<Kerkesa> sc=new ArrayList();
		List<Doc> dc=new ArrayList(); 
		for(Kerkesa c:kerkesaService.listAll()) {
			if(c.getStudent().getId()==student.getId()) {
				sc.add(c);
				dc.add(c.getFile());
			}
		}
		
		 updtUser=userService.getUser(student.getEmail()); 
		mav.addObject("docs",dc); 
		mav.addObject("listKerkesash",sc);
		 
 
	    mav.addObject("student", student);
	      
	    return mav;
	}
	
	 

	@RequestMapping("/Student/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") int id) {
		studentService.delete(id);
	    return "success";       
	}
	
	
}