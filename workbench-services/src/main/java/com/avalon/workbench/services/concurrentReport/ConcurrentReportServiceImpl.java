package com.avalon.workbench.services.concurrentReport;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avalon.workbench.beans.concurrntReport.Inputs;
import com.avalon.workbench.repository.concurrentReport.ConcurrentReportInputsRepository;
import com.avalon.workbench.repository.concurrentReport.ConcurrentReportRepository;
import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;
import com.avalon.workbench.services.exception.WorkbenchServiceException;

@Service("ConcurrentReportServiceImpl")
public class ConcurrentReportServiceImpl implements ConcurrentReportService {
	protected static final Logger LOG_R = Logger
			.getLogger(ConcurrentReportServiceImpl.class);

	@Autowired
	@Qualifier(value = "concurrentReportRepositoryImpl")
	ConcurrentReportRepository concurrentReportRepository;

	@Autowired
	@Qualifier(value = "ConcurrentReportInputsServiceImpl")
	ConcurrentReportInputsService service;

	public String getConcurrentReport(String respName, String uname,
			String shortName, String concurrentName, ArrayList<String> params) throws WorkbenchServiceException,
			InterruptedException {
		try {
			String reqId;
			String fileName;
			Inputs inputs = service.getInputs(concurrentName);
			if (inputs != null) {
				reqId = concurrentReportRepository.generateConcurrentReport(
						respName, uname, shortName, concurrentName, inputs, params);
				fileName = concurrentName + "_" + reqId + "_1.PDF";
				LOG_R.info(" inside if....new fileName==" + fileName);
				Thread.sleep(30000);
				concurrentReportRepository.getConcurrentReport(fileName);
			} else {
				reqId = concurrentReportRepository.generateConcurrentReport(
						respName, uname, shortName, concurrentName, null, params);
				fileName = "o"+ reqId+".out";
				LOG_R.info("new fileName==" + fileName);
				Thread.sleep(30000);
				concurrentReportRepository.getConcurrentReport(fileName);
			}
			LOG_R.info("file name sent to controller=="+fileName);
			return fileName;
		} catch (WorkbenchDataAccessException e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchServiceException(e);
		}

	}

}
