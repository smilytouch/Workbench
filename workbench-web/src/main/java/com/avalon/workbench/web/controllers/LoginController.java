package com.avalon.workbench.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.services.responsibilites.ResponsibilitesService;
import com.avalon.workbench.services.user.UserService;

@Controller
public class LoginController {

	protected static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	@Qualifier("UserServiceImpl")
	UserService service;

	@Autowired
	@Qualifier("ResponsibilitesServiceImpl")
	ResponsibilitesService responsibilitesService;

	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String checkUser(String uname,Model model) {
		log.info("inside checkUser......UserService==" + service);
		boolean flag = service.AuthenticateUser(uname);
		if (flag) {
			List<Responsibilites> responsibilites = responsibilitesService
					.getResonsibilites("uname");
			model.addAttribute("allResponsibilites", responsibilites);
			return "Responsibilites";
		} else
			return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws Exception {
		return "login";
	}
}
