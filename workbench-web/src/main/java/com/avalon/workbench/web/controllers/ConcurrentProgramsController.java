package com.avalon.workbench.web.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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
import com.avalon.workbench.services.concurrentPrograms.ConcurrentProgramsService;
import com.avalon.workbench.services.concurrentReport.ConcurrentReportService;
import com.avalon.workbench.services.exception.WorkbenchServiceException;

@Controller
public class ConcurrentProgramsController {
	protected static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("ConcurrentProgramsServiceImpl")
	ConcurrentProgramsService concurrentProgramsService;
	
	@Autowired
	@Qualifier("ConcurrentReportServiceImpl")
	ConcurrentReportService ConcurrentReportServiceImpl;
	
	@RequestMapping(value = "/getConcurrentPrograms", method = RequestMethod.GET)
	public String getConcurrentPrograms(@RequestParam("pageId") int pageId,HttpSession session, Model model,String respName) throws WorkbenchServiceException {
		log.info("inside getConcurrentPrograms......");
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
	
	@RequestMapping(value = "/getReport", method = RequestMethod.GET)
	public void getFile(String respName,String progName,HttpServletResponse response,HttpSession session) {
	    try {
	    	String uname=(String) session.getAttribute("uname");
	    	String fileName=ConcurrentReportServiceImpl.getConcurrentReport(respName, uname, progName);
	      // get your file as InputStream
	      InputStream is = new FileInputStream("F:/"+fileName+".pdf");
	      // copy it to response's OutputStream
	      IOUtils.copy(is, response.getOutputStream());
	      response.setContentType("application/pdf");
	      response.flushBuffer();
	    } catch (Exception ex) {
	      log.info("Error writing file to output stream. Filename was");
	      throw new RuntimeException("IOError writing file to output stream");
	    }

	}
}
