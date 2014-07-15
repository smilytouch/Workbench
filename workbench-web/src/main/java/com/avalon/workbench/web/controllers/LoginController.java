package com.avalon.workbench.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avalon.workbench.repository.user.UserRepository;
import com.avalon.workbench.services.user.UserService;

@Controller
public class LoginController {

	protected static final Logger log = Logger
			.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("UserServiceImpl")
	UserService service;
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String checkUser(String uname,String pass) {
		log.info("inside checkUser......UserService=="+service);
		boolean flag=service.AuthenticateUser(uname, pass);
		if(flag)
			return "sanjay";
		else
			return "login";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login()throws Exception{
		return "login";
	}
}
