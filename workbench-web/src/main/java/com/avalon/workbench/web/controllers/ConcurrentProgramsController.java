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

import com.avalon.workbench.beans.concurrntPrograms.ConcurrentPrograms;
import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.services.concurrentPrograms.ConcurrentProgramsService;
import com.avalon.workbench.services.exception.WorkbenchServiceException;
import com.avalon.workbench.services.responsibilites.ResponsibilitesService;

@Controller
public class ConcurrentProgramsController {
	protected static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("ConcurrentProgramsServiceImpl")
	ConcurrentProgramsService concurrentProgramsService;
	
	@RequestMapping(value = "/getConcurrentPrograms", method = RequestMethod.GET)
	public String getConcurrentPrograms(@RequestParam("pageId") int pageId,HttpSession session, Model model,String respName) throws WorkbenchServiceException {
		log.info("inside getResponsibilities......");
		String uname=(String) session.getAttribute("uname");
		List<ConcurrentPrograms> programs = concurrentProgramsService.getConcurrentPrograms(uname, respName);
		PagedListHolder<ConcurrentPrograms> pagedListHolder = new PagedListHolder<ConcurrentPrograms>(programs);
		int page = pageId > 0 ? pageId : 0;
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("respName", respName);
		return "ConcurrentPrograms";
	}
}
