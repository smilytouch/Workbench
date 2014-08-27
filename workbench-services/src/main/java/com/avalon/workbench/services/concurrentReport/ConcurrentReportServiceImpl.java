package com.avalon.workbench.services.concurrentReport;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avalon.workbench.beans.concurrntReport.Inputs;
import com.avalon.workbench.repository.concurrentReport.ConcurrentReportInputsRepository;
import com.avalon.workbench.repository.concurrentReport.concurrentReportRepository;
import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;
import com.avalon.workbench.services.exception.WorkbenchServiceException;

@Service("ConcurrentReportServiceImpl")
public class ConcurrentReportServiceImpl implements ConcurrentReportService {
	protected static final Logger LOG_R = Logger
			.getLogger(ConcurrentReportServiceImpl.class);

	@Autowired
	@Qualifier(value = "concurrentReportRepositoryImpl")
	concurrentReportRepository concurrentReportRepository;
	
	@Autowired
	@Qualifier(value = "ConcurrentReportInputsServiceImpl")
	ConcurrentReportInputsService service;

	public String getConcurrentReport(String respName, String uname,
			String shortName, String progName, String concurrentName, ArrayList<String> params) throws WorkbenchServiceException, InterruptedException {
		try {
			LOG_R.info("respName===="+concurrentName);
			Inputs inputs=service.getInputs(concurrentName);
			LOG_R.info("shortname==="+inputs.getApplication_Short_Name()+inputs.getDefault_Language());
			String reqId=concurrentReportRepository.generateConcurrentReport(respName, uname,shortName, progName, inputs, params);
			String newProgName=concurrentName+"_"+reqId+"_1";
			LOG_R.info("new progName==" + newProgName);
			Thread.sleep(20000);
			concurrentReportRepository.getConcurrentReport(newProgName);
			return newProgName;
		} catch (WorkbenchDataAccessException e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchServiceException(e);
		}

	}

}
