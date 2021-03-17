package com.example.demoUni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoUni.entity.Dega;
import com.example.demoUni.service.DegaService; 

@Controller
public class TestController {
	/*
	 * @Autowired private BranchService branchService;
	 */
	/*
	 * @RequestMapping("/about") public String showaboutpage() { return "about"; }
	 */
	@RequestMapping("/contact")
	public String showContactpage() {
		return "contact";
	}
	@RequestMapping("/fshn")
	public String showfshnpage() {
		return "fshn";
	}
	@RequestMapping("/law")
	public String showFLawpage() {
		return "law";
	}
	@RequestMapping("/economy")
	public String showFEconomypage() {
		return "economy";
	}
	@RequestMapping("/mjeksi")
	public String showFmjpage() {
		return "mjeksi";
	}
//	@RequestMapping("/login5")
//		public String showLoginpage() {
//			return "login5";
//		}
	/*
	 * @GetMapping("/student-form/addBranchList") public ModelAndView
	 * redirectListBranches(Model model) { ModelAndView modelAndview = new
	 * ModelAndView("student-form"); List<Branch> listBranches = (List<Branch>)
	 * branchService.listAllBranches(); model.addAttribute("listBranches",
	 * listBranches); model.addAttribute("showMyCourseList", 1); return
	 * modelAndview; }
	 */
	
}
