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

import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.Dekani;
import com.example.demoUni.entity.Kerkesa;
import com.example.demoUni.entity.LENDEstudent;
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.entity.Mesazh;
import com.example.demoUni.entity.Pedagog;
import com.example.demoUni.entity.PedagogDege;
import com.example.demoUni.entity.PedagogLende;
import com.example.demoUni.entity.Sekretari;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.User;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.DekaniService;
import com.example.demoUni.service.LendaService;
import com.example.demoUni.service.MesazhService;
import com.example.demoUni.service.PedagogService;
import com.example.demoUni.service.StudentService;
import com.example.demoUni.service.UserService;

@Controller
public class DekaniViewController {

	
	@Autowired DekaniService dekaniService;
	@Autowired DegaService degaService;
	@Autowired LendaService lendaService;
	@Autowired PedagogService pedagogService;
	@Autowired MesazhService mesazhService;
	@Autowired UserService userService;
	@Autowired StudentService studentService;
	List <Dega>listdege1;
	List <Lenda>listL1;
	List <Lenda>listL2;
	List <Student>listS1;
	Dega DEGA;
	Lenda LENDA;
	
	@RequestMapping("/dekani-form")
	public ModelAndView showStudent() {
		 ModelAndView mav = new ModelAndView("dekani-form");
		mav.addObject("Fillimi", 1);
		return mav;
	}
	
	@GetMapping("/dekani-form/showData")
	public ModelAndView indexOfStudents(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Dekani d= dekaniService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    model.addAttribute("dekani", d);
	    model.addAttribute("showDekani", 1);
	        return modelAndview;
	} 
	
	@RequestMapping("/dekani-form/newDega")
	public ModelAndView showNewStudentPage(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
	    Dega d=new Dega();
	    model.addAttribute("dega",d);
	    model.addAttribute("showCreateNewDega", 1);
	    return modelAndview;
	}
	
	@GetMapping("/dekani-form/ListaEDegeve")
	public ModelAndView  ListBranches(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form"); 
		
		List<Dega> sc=new ArrayList();
		 
		for(Dega c:degaService.listAll()) {
			  sc.add(c);				
		}
		 
	    model.addAttribute("listDeget", sc);
	    model.addAttribute("showDegetlist", 1);
	        return modelAndview;
	}
	
	@GetMapping("/dekani-form/ListaElendevedekani")
	public ModelAndView redirectLis(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form"); 
	    model.addAttribute("listDege", degaService.listAll());
	    model.addAttribute("listLende", lendaService.listAll());
	    model.addAttribute("showLendeList", 1);
	        return modelAndview;
	}
	
	@RequestMapping(value ="/dekani-form/listByLende", method = RequestMethod.POST)
	public ModelAndView listmybran (Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
	 
		List <Lenda>listLende=lendaService.FindByDega(id);
	    model.addAttribute("listLende",listLende);
		model.addAttribute("listDege", degaService.listAll());
		model.addAttribute("showLendeList", 1);
        return modelAndview;
	}
	
	@RequestMapping("/dekani-form/newLenda")
	public ModelAndView showNewLendaPage(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
	    Lenda lenda = new Lenda();
	    model.addAttribute("lenda", lenda);
	    model.addAttribute("listDege", degaService.listAll()); 
	    model.addAttribute("showCreateNewLende", 1);
	    return modelAndview;
	}
	@GetMapping("/dekani-form/ListaPedagogeve")
	public ModelAndView reddirectLis(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form"); 
	    model.addAttribute("listDege", degaService.listAll());
	    model.addAttribute("listPedagog", pedagogService.listAll());
	    model.addAttribute("showPedagogList", 1);
	        return modelAndview;
	}
	@RequestMapping(value ="/dekani-form/listPedagogByDega", method = RequestMethod.POST)
	public ModelAndView listmybranrt (Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		
			List <Pedagog>listP=new ArrayList();
			List<Pedagog> pl=pedagogService.listAll();
		    for(Pedagog p: pl  ) {
		    	for(PedagogDege pdege:p.getDega()) {
		    		//System.out.println("Hyri--PedagogDege pdege:p.getDega()--->"+pdege.getDega().getEmri()+"<---");
		    		if(pdege.getDega().getEmri().equals(degaService.get(id).getEmri()))
		    		{
		    			//System.out.println("pdege dega"+pdege.getDega().getEmri()+"-----degaService.get(id)   "+degaService.get(id));
		    			listP.add(pdege.getPedagog());
		    			//System.out.println(pdege.getPedagog());
		    		}
		    	}
		    }
	    model.addAttribute("listPedagog",listP);
		model.addAttribute("listDege", degaService.listAll());
		model.addAttribute("showPedagogList", 1);
        return modelAndview;
	}
	
	
	@GetMapping("/dekani-form/addMesazh")
	  public ModelAndView redirectmesazhForm(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Dekani st=dekaniService.findByEmail(auth.getName());
	    model.addAttribute("dekani", st);
	   // model.addAttribute("users", userService.listAll()); 
		 Mesazh ms=new Mesazh();
		 model.addAttribute("mesazh", ms);
		model.addAttribute("showNewmesazhh", 1);
	      return modelAndview;
	}
	
	@GetMapping("/dekani-form/addMesazhDerg")
	public ModelAndView indexOfStudents2(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Dekani st=dekaniService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    
	    List<Mesazh> sc=new ArrayList();
		for(Mesazh c:mesazhService.listAll()) {
			if(c.getEmailD().equals(st.getEmail())) {
				sc.add(c);
				
			} 
		}
		  
		model.addAttribute("listMesazhe",sc);
		model.addAttribute("dekani", st);
		 
		 
	    model.addAttribute("showMesDergg", 1);
	        return modelAndview;
	} 
	
	
	@GetMapping("/dekani-form/addMesazhMarr")
	public ModelAndView indexOfStudents3(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Dekani st=dekaniService.findByEmail(auth.getName());
	    //List<Student> listStudents = studentService.listAll();
	    List<Mesazh> sc=new ArrayList();
		for(Mesazh c:mesazhService.listAll()) {
			if(c.getEmailM().equals(st.getEmail())) {
				sc.add(c);
				
			} 
		}
		 
		model.addAttribute("listMesazhe",sc);
		model.addAttribute("dekani", st);
		 
		 
	    model.addAttribute("showMesMarrr", 1);
	        return modelAndview;
	} 
	
	
	@RequestMapping("/Dekani/view/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Dekani/view_msg");
	    Mesazh msg = mesazhService.get(id);
	     
	    User s=new  User();
		for( User c:userService.listAll()) {
			if(c.getEmail().equals(msg.getEmailD())) {
				s=c;
				
			} 
		}

	    mav.addObject("mesazh", msg);
	    mav.addObject("dekani", s);  
	    return mav;
	}
	
	
	@RequestMapping("/Dekani/viewM/{id}")
	public ModelAndView showEPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Dekani/view_msgM");
	    Mesazh msg = mesazhService.get(id);
	     
	    User s=new  User();
		for( User c:userService.listAll()) {
			if(c.getEmail().equals(msg.getEmailM())) {
				s=c;
				
			}  
		}

	    mav.addObject("mesazh", msg);
	    mav.addObject("dekani", s);  
	    return mav;
	}
	
	@GetMapping("/dekani-form/listoStudenteNeBazTedegesDheLendeveQeJEp")
	public ModelAndView inents(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Pedagog p= pedagogService.findByEmail(auth.getName());
	    List <PedagogDege>listPdege=p.getDega();
	    List <Dega>listdege=new ArrayList();
	    for(PedagogDege d:listPdege) {
	    	if(d.getPedagog().getId()==p.getId()) {
	    		listdege.add(d.getDega());
	    	}
	    }
	    listdege1=listdege;
	    List <PedagogLende>listPlenda=p.getLende();
	    List <Lenda>listL=new ArrayList();
	    for(PedagogLende d:listPlenda) {
	    	if(d.getPedagog().getId()==p.getId()) {
	    		listL.add(d.getLenda());
	    	}
	    }
	    listL1=listL;
	    List <Student>listS=new ArrayList();
	    for (Dega d:listdege) {
	    	for(Student s:studentService.listAll()) {
	    		if(s.getDega().getId()==d.getId()) {
	    			listS.add(s);
	    		}
	    	}
	    	
	    }
	    listS1=listS;
	    model.addAttribute("listdege", listdege);
	    model.addAttribute("listL", listL);
	    model.addAttribute("listS", listS);
	    
	    model.addAttribute("showStudent", 1);
	        return modelAndview;
	}
	@RequestMapping(value ="/dekani-form/listolendetsipasDegesP", method = RequestMethod.POST)
	public ModelAndView listmybranches(Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		List <Lenda>listL=new ArrayList();
		for(Lenda l:listL1) {
			if(l.getDega().getId()==id) {
				listL.add(l);
			}
		}
		
		 listL2=listL;
		 List <Student>listS=studentService.listByDega(id);
	    model.addAttribute("listL", listL);
		model.addAttribute("listdege", listdege1);
		 model.addAttribute("listS", listS);
		model.addAttribute("list2", degaService.get(id));
		DEGA=degaService.get(id);
		 model.addAttribute("showStudent", 1); 
		
        return modelAndview;
        
	}
	@RequestMapping(value ="/dekani-form/listolendetlistP", method = RequestMethod.POST)
	public ModelAndView listmybrahes(Model model,@RequestParam(value="lende",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		   
		List <Student>listS=new ArrayList(); 
		List<LENDEstudent> lista=new ArrayList<LENDEstudent>(); 
		for(Student s:studentService.listAll()) {
			for(LENDEstudent ls:s.getLendStudent()) {
				if(ls.getLenda().getId()==id) {
					listS.add(ls.getStudent());
				}
			}
		}
		LENDA=lendaService.get(id); 
		model.addAttribute("listL", listL2);
		model.addAttribute("listdege", listdege1);
		model.addAttribute("listS", listS);
		model.addAttribute("list1", lendaService.get(id));
		model.addAttribute("list2", DEGA);
		model.addAttribute("showStudent", 1);
        return modelAndview;
        
	}
	
	@RequestMapping("/dekani-form/studentPayment/{id}")
	public String showPayment(@PathVariable(name = "id") long id,Model model) {
		Student student=studentService.get(id);
		LENDEstudent ls=new LENDEstudent();
		for(LENDEstudent lendstudent:student.getLendStudent()) {
			if(lendstudent.getStudent().getId()==id && lendstudent.getLenda().getId()==LENDA.getId()) {
				ls=lendstudent;
			}
		}
		model.addAttribute("courses",ls);
		model.addAttribute("student",student);
		return "studentPayment1";
	}
	
	@RequestMapping(value="/savePayment1",method = RequestMethod.POST) 
	public String saveStudentPayments(Model model,@RequestParam(value="studentId",required=false)Long studentId,@RequestParam(value="nota",required =true) String nota ) {
		
	  Student student=studentService.get(studentId);
	  List<LENDEstudent> lista=new ArrayList<LENDEstudent>(); 
	  
	   
	  LENDEstudent sc=new LENDEstudent();
	  sc.setLenda(LENDA);
	  sc.setNota(nota);
	  sc.setStudent(student);
	  lista.add(sc);
	 
	  student.setLendStudent(lista);	  
	  studentService.save(student); 
	  return "redirect:/dekani-form"; 
	  }
	
}
