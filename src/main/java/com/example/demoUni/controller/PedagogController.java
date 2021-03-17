package com.example.demoUni.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Dega;
import com.example.demoUni.entity.LENDEstudent;
import com.example.demoUni.entity.Lenda;
import com.example.demoUni.entity.Role;
import com.example.demoUni.entity.Student;
import com.example.demoUni.entity.Pedagog;
import com.example.demoUni.entity.PedagogDege;
import com.example.demoUni.entity.PedagogLende;
import com.example.demoUni.entity.User;
import com.example.demoUni.service.DegaService;
import com.example.demoUni.service.PedagogService;
import com.example.demoUni.service.RoleService;
import com.example.demoUni.service.UserService;
import com.example.demoUni.service.LendaService;
@Controller
public class PedagogController {

  	@Autowired private DegaService degaService;
    @Autowired private PedagogService pedagogService;
    @Autowired private LendaService lendaService;
	@Autowired private RoleService roleService;
	@Autowired private UserService userService;
	static User updtUser;
    Dega DEGA;
    Lenda LENDA;
    Pedagog P;
	@RequestMapping("/Pedagog/new")
	public ModelAndView showPedagogPage(Model model) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
	    Pedagog pedagog = new Pedagog();
	    model.addAttribute("pedagog", pedagog);
	    model.addAttribute("listDege", degaService.listAll());
	    model.addAttribute("listLende",lendaService.listAll());
	    model.addAttribute("showCreateNewPedagog", 1);
	    return modelAndview;
	}
	@RequestMapping(value ="/listolendetsipasDeges", method = RequestMethod.POST)
	public ModelAndView listmybranches(Model model,@RequestParam(value="dega",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Pedagog pedagog = new Pedagog();
	    model.addAttribute("pedagog", pedagog); 
		List <Lenda>listLende=lendaService.FindByDega(id);
	    model.addAttribute("listLende", listLende);
		model.addAttribute("listDege", degaService.listAll());
		model.addAttribute("list2", degaService.get(id));
		 model.addAttribute("showCreateNewPedagog", 1); 
		DEGA=degaService.get(id); 
        return modelAndview;
        
	}
	
	@RequestMapping(value ="/listolendetlist", method = RequestMethod.POST)
	public ModelAndView listmybrahes(Model model,@RequestParam(value="lende",required =true) long id) {
		ModelAndView modelAndview = new ModelAndView("dekani-form");
		Pedagog pedagog = new Pedagog();
	    model.addAttribute("pedagog", pedagog);  
	    model.addAttribute("listLende", lendaService.FindByDega(DEGA.getId()));
		model.addAttribute("listDege", degaService.listAll());
		model.addAttribute("list1", lendaService.get(id));
		model.addAttribute("list2", lendaService.get(id).getDega());
		LENDA=lendaService.get(id); 
		model.addAttribute("showCreateNewPedagog", 1);
        return modelAndview;
        
	}
	
	
	@RequestMapping("/Pedagog/index")
	public String showPedagogList(Model model) {
	List<Pedagog> listPedagog = pedagogService.listAll();
    model.addAttribute("listPedagog", listPedagog); 
    return "Pedagog/index"; 
	}
	
	 
	@RequestMapping(value = "/savePedagog", method = RequestMethod.POST)
	public String savePedagog(@ModelAttribute("pedagog") Pedagog pedagog) {
		 
		List<PedagogDege> sc=new ArrayList();
		List<PedagogLende> sd=new ArrayList();
		PedagogDege pd=new PedagogDege();
		pd.setDega(DEGA);
		pd.setPedagog(pedagog);
		PedagogLende pl=new PedagogLende();
		pl.setLenda(LENDA);
		pl.setPedagog(pedagog);
		sc.add(pd);
		sd.add(pl);
		pedagog.setDega(sc);
		pedagog.setLende(sd);
		
		pedagogService.save(pedagog);
		
		User user=new User();
		user.setEmail(pedagog.getEmail());
		user.setLastname(pedagog.getLastname());
		user.setName(pedagog.getFirstname());
		user.setPassword(pedagog.getPass());
		Role role=roleService.getRole(3);
		Set<Role> roles=new HashSet<>();
		roles.add(role); 
		user.setRole(roles);
		user.setPassword(pedagog.getPass());		
		userService.saveUser(user);
		
	    return "redirect:/dekani-form";
	}
	
	@RequestMapping(value = "/saveP", method = RequestMethod.POST)
	public String savePed(@ModelAttribute("pedagog") Pedagog pedagog ) {
		  pedagogService.save(pedagog);
		  
		  return "redirect:/dekani-form";
	}
	
	 
	
	
	
	@RequestMapping("/Pedagog/edit/{id}")
	public ModelAndView showEditPedagogPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Dekani/edit_pedagog");
	    Pedagog pedagog = pedagogService.get(id);
	    List<PedagogLende> pl=pedagog.getLende();
	    
	    List<Lenda> lend=new ArrayList();
	    for(PedagogLende prov:pl) {
	    	if(prov.getPedagog().getId()==id) {
	    		lend.add(prov.getLenda());
	    	}
	    }
	    
	    
	    mav.addObject("lendet",lend);
	    mav.addObject("pedagog", pedagog); 
		 
		 return mav;
	}
	
	//pedagog-form
	@RequestMapping("/Pedagog/editPedagog/{id}")
	public ModelAndView showEedagogPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Pedagog/edit_pedagog");
	    Pedagog pedagog = pedagogService.get(id);
	    List<PedagogLende> pl=pedagog.getLende();
	    List<Lenda> lend=new ArrayList();
	    for(PedagogLende prov:pl) {
	    	if(prov.getPedagog().getId()==id) {
	    		lend.add(prov.getLenda());
	    	}
	    }
	    mav.addObject("lendet",lend);
	    mav.addObject("pedagog", pedagog); 
		//mav.addObject("lendet",lendaService.FindByDega( pedagog.getDega().getId()));
		
	    updtUser=userService.getUser(pedagog.getEmail());   
		 return mav;
	}
	 
	
	@RequestMapping("/Pedagog/shtolend/{id}")
	public ModelAndView showEdedagogPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("Dekani/shto_lende");
	    Pedagog pedagog = pedagogService.get(id);
	    P=pedagog;
	    PedagogLende pl=new PedagogLende();
	    mav.addObject("pedlend", pl);
	    mav.addObject("listLende",lendaService.listAll());
	   
		//mav.addObject("lendet",lendaService.FindByDega( pedagog.getDega().getId()));
		
		
		 return mav;
	}
	
	@RequestMapping(value = "/savepedLenda", method = RequestMethod.POST)
	public String savePedd(@ModelAttribute("pedlend") PedagogLende pl ) {
		   pl.setPedagog(P);
		    
		   Dega dega1=pl.getLenda().getDega();
		   
		   try
		    {
		      // create a mysql database connection
		      String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost:3306/demounidb?useSSL=false&allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=UTC";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "anisA123");
		    
		      

		      // the mysql insert statement
		      String query = " insert into pedagog_lende (pedagog_id, lenda_id)"
		        + " values (?, ?)";
		      

		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setLong (1, P.getId());
		      preparedStmt.setLong (2, pl.getLenda().getId());
		      
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      
		      
		      String query1 = " insert into pedagog_dege (pedagog_id, dega_id)"
		  		        + " values (?, ?)";
		    	  
		    	  PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
			      preparedStmt1.setLong (1, P.getId());
			      preparedStmt1.setLong (2, pl.getLenda().getDega().getId());
			      preparedStmt1.execute();
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
 
		   
		  return "redirect:/dekani-form";
	}
 

	@RequestMapping("/Pedagog/delete/{id}")
	public String deletePedagog(@PathVariable(name = "id") int id) {
		pedagogService.delete(id);
	    return "success";       
	} 
}
