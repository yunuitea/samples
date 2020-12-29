package com.example.samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/login")
public class LoginViewController {
	
	private String id = "admin";
	private String pw = "Admin1234";
	
	@RequestMapping(value="/loginForm")
	public ModelAndView loginForm() {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/login");
		return view;
	}
	
	@RequestMapping(value="/success")
	public ModelAndView successView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/success");
		return view;
	}
	
	@RequestMapping(value="/proc/login")
	public String loginProc(@RequestParam(value = "account_id") String accountId,
			                             @RequestParam(value = "account_pw") String accountPw) {
		
		ModelAndView view = new ModelAndView();
		String url;
		
		if(id.equals(accountId) && pw.equals(accountPw)) {
			url  =  "forward:/book/list";
		}else {
			url = "login/fail";
		}
		
		return url;
	}
	
	
}
