package com.example.demoUni.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	
	@RequestMapping(value = {"/login"},method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		System.out.println("hyrii");
		model.setViewName("user/login2");
		return model;
	}
	
	
    @RequestMapping(value = "/ridirektUser", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest httpServletRequest, Model model) {
	if(httpServletRequest.isUserInRole("dekani")) {
	return "/dekani-form";
	} else if(httpServletRequest.isUserInRole("sekretari")) {
            return "/acc-form";
	} else if(httpServletRequest.isUserInRole("pedagog")) {
        return "/pedagog-form";
      } else {
            return "/student-form";
        }
    }
	/*
	 * @RequestMapping("/index") public String viewHomePage(Model model) { return
	 * "images"; }
	 */
}
