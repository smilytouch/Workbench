package com.avalon.workbench.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.services.exception.WorkbenchServiceException;
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

	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public String checkUser(String uname, Model model,HttpSession session) throws Exception {
		log.info("inside checkUser......");
		boolean flag = service.AuthenticateUser(uname);
		if (flag){
			session.setAttribute("uname", uname);
			return "redirect:getResponsibilities?pageId=0";
		}
		else{
			model.addAttribute("status", "invalid Username");
			return "redirect:login";
		}
			
	}

	@RequestMapping(value = "/getResponsibilities", method = { RequestMethod.GET, RequestMethod.POST })
	public String getResponsibilities(@RequestParam("pageId") int pageId,HttpSession session, Model model) throws WorkbenchServiceException {
		log.info("inside getResponsibilities......");
		String uname=(String) session.getAttribute("uname");
		log.info("unameeeeee=="+session.getAttribute("uname"));
		List<Responsibilites> responsibilites = responsibilitesService
				.getResonsibilites(uname);
		PagedListHolder<Responsibilites> pagedListHolder = new PagedListHolder<Responsibilites>(
				responsibilites);
		int page = pageId > 0 ? pageId : 0;
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("allResponsibilites", responsibilites);
		return "Responsibilites";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws Exception {
		return "login";
	}
}
