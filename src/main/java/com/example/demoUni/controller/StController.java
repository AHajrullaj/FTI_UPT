package com.example.demoUni.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

 
import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.Doc;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.LENDEstudent;
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.entity.Mesazh;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.User;
//import com.abp.paymentSystem.entity.StudentCourse;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.KerkesaService;
import com.example.demoUni.service.LendaService;
import com.example.demoUni.service.MesazhService;
import com.example.demoUni.service.CustomUserDetailsService;
import com.example.demoUni.service.StudentService;
import com.example.demoUni.service.UserService;


//@PreAuthorize("hasAnyRole('STUDENT')")
@Controller
public class StController {
	@Autowired
	DegaService degaService;
	@Autowired
	StudentService studentService;
	@Autowired
	LendaService lendaService;
	@Autowired UserService userService;
	@Autowired
	CustomUserDetailsService userservice;
	@Autowired KerkesaService kerkesaService;
	@Autowired private MesazhService mesazhService;
	@RequestMapping("/student-form")
	public ModelAndView showStudent() {
		 ModelAndView mav = new ModelAndView("student-form");
		mav.addObject("Fillimi", 1);
		return mav;
	}

	

//	@GetMapping("/student-form/addCourseList")
//	public ModelAndView redirectListCourses(Model model) {
//				
//		ModelAndView modelAndview = new ModelAndView("student-form");		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
//		List <Branch>listBranches=branchService.listAllBranches();
////		List <Course>listCourses =studentService.get(31).getCourses();
//	    model.addAttribute("listBranches", listBranches);
////	    model.addAttribute("listCourses", listCourses);
//	    model.addAttribute("showMyCourseList", 1);
//	        return modelAndview;
//	} 
//	 

	@GetMapping("/student-form/addCourseList")
	public ModelAndView redirectListCourses(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Student student=studentService.findByEmail(auth.getName());
		Dega dega=degaService.get(student.getDega().getId());
		
		ModelAndView modelAndview = new ModelAndView("student-form");
		//List <Dega>listDega=degaService.listAll();
		//List<Lenda> lenda=new ArrayList();
		List<LENDEstudent> lendet=student.getLendStudent();
		/*
		 * List<Lenda>listLenda =lendaService.listAll(); for(Lenda c:listLenda) {
		 * 
		 * if(c.getDega().getId()==dega.getId()) lenda.add(c); }
		 */
	   // model.addAttribute("listDega", listDega);
	    model.addAttribute("listLenda", lendet);
	    model.addAttribute("showMyCourseList", 1);
	        return modelAndview;
	} 
	 

	
	@RequestMapping(value ="/student-form/addCourseList1", method = RequestMethod.POST)
	public ModelAndView indexOfStu1dents(Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		List<Lenda> listLenda = lendaService.FindByDega(id);
		List <Dega>listDega=degaService.listAll();
	    model.addAttribute("listDega", listDega);
		model.addAttribute("listLenda", listLenda);
		model.addAttribute("showMyCourseList", 1);
        return modelAndview;
	}
	
	@GetMapping("/student-form/addStudentList")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Student st=studentService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    model.addAttribute("listStudents", st);
	    model.addAttribute("showMyStudentList", 1);
	        return modelAndview;
	} 

	@GetMapping("/student-form/addKerkesaList")
	public ModelAndView indexOfStudents1(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Student st=studentService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    
	    List<Kerkesa> sc=new ArrayList();
		 
		for(Kerkesa c:kerkesaService.listAll()) {
			if(c.getStudent().getId()==st.getId()) {
				sc.add(c);
				 
			}
		}
		
		  
		 
		model.addAttribute("listKerkesash",sc);
	    
	    
	    model.addAttribute("listStudents", st);
	    model.addAttribute("showKerkesaList1", 1);
	        return modelAndview;
	} 
	
	
	@RequestMapping("/Student/view/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Student/view_msg");
	    Mesazh msg = mesazhService.get(id);
	     
	    User s=new  User();
		for( User c:userService.listAll()) {
			if(c.getEmail().equals(msg.getEmailD())) {
				s=c;
				
			} 
		}
 
	    mav.addObject("mesazh", msg);
	    mav.addObject("student", s);  
	    return mav;
	}
	
	
	@RequestMapping("/Student/viewM/{id}")
	public ModelAndView showEPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Student/view_msgM");
	    Mesazh msg = mesazhService.get(id);
	     
	    User s=new  User();
		for( User c:userService.listAll()) {
			if(c.getEmail().equals(msg.getEmailD())) {
				s=c;
				
			} 
		}
 
	    mav.addObject("mesazh", msg);
	    mav.addObject("student", s);  
	    return mav;
	}
	
	
	@GetMapping("/student-form/addNewKerkesa")
	  public ModelAndView redirectCourseForm(Model model) {
			ModelAndView modelAndview = new ModelAndView("student-form");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Student st=studentService.findByEmail(auth.getName());
		    model.addAttribute("dega", degaService.listAll()); 
		    model.addAttribute("student", st);
		    
	    	Kerkesa   kerkesa = new Kerkesa();
	    	model.addAttribute("kerkesa",  kerkesa);
		    List<String>  listkategori = Arrays.asList("vertetim studenti", "list notash");
		    model.addAttribute("listkategori",  listkategori);
		   
		    
		    
	      model.addAttribute("showNewKerkes", 1);
	      return modelAndview;
		}
	
	
	@GetMapping("/student-form/KerkesaMarra")
	  public ModelAndView redirectCourseForma(Model model) {
			ModelAndView modelAndview = new ModelAndView("student-form");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Student st=studentService.findByEmail(auth.getName());
		     
		   
		    
		    List<Kerkesa> sc=new ArrayList();
			List<Doc> dc=new ArrayList(); 
			for(Kerkesa c:kerkesaService.listAll()) {
				if(c.getStudent().getId()==st.getId()) {
					sc.add(c);
					if(c.isDerguar()) {
						dc.add(c.getFile());
					}
				}
			}
			 
			model.addAttribute("docs",dc); 
			model.addAttribute("listKerkesash",sc);
			 model.addAttribute("student", st);
		    
		    
	      model.addAttribute("showDokumenta", 1);
	      return modelAndview;
		}
	
	@GetMapping("/student-form/addMesazh")
	  public ModelAndView redirectmesazhForm(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Student st=studentService.findByEmail(auth.getName());
	    model.addAttribute("student", st);
	   // model.addAttribute("users", userService.listAll()); 
		 Mesazh ms=new Mesazh();
		 model.addAttribute("mesazh", ms);
		model.addAttribute("showNewmesazh", 1);
	      return modelAndview;
	}
	
	@GetMapping("/student-form/addMesazhDerg")
	public ModelAndView indexOfStudents2(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Student st=studentService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    
	    List<Mesazh> sc=new ArrayList();
		for(Mesazh c:mesazhService.listAll()) {
			if(c.getEmailD().equals(st.getEmail())) {
				sc.add(c);
				
			} 
		}
		 
		model.addAttribute("listMesazhe",sc);
		model.addAttribute("student", st);
		 
		 
	    model.addAttribute("showMesDerg", 1);
	        return modelAndview;
	} 
	
	
	@GetMapping("/student-form/addMesazhMarr")
	public ModelAndView indexOfStudents3(Model model) {
		ModelAndView modelAndview = new ModelAndView("student-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Student st=studentService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    List<Mesazh> sc=new ArrayList();
		for(Mesazh c:mesazhService.listAll()) {
			if(c.getEmailM().equals(st.getEmail())) {
				sc.add(c);
				
			} 
		}
		 
		model.addAttribute("listMesazhe",sc);
		model.addAttribute("student", st);
		 
		 
	    model.addAttribute("showMesMarr", 1);
	        return modelAndview;
	} 
	@RequestMapping("/student-form/edit/{id}")
	public ModelAndView showEditFinancePage(@PathVariable(name = "id") long id) {
	    ModelAndView mav = new ModelAndView("student-form");
	    Student student=studentService.get(id);
	    mav.addObject("student", student);	
	    mav.addObject("showEditStudent", 1);
	    
	    return mav;
	}
}
