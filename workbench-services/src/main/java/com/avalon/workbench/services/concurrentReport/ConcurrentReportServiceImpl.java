package com.avalon.workbench.services.concurrentReport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

	public String getConcurrentReport(String respName, String uname,
			String progName) throws WorkbenchServiceException, InterruptedException {
		try {
			String reqId=concurrentReportRepository.runConcurrentProgram(respName, uname,progName);
			String newProgName=progName.toUpperCase()+"_"+reqId+"_1";
			LOG_R.info("new progName==" + newProgName);
			Thread.sleep(17000);
			concurrentReportRepository.getConcurrentReport(newProgName);
			return newProgName;
		} catch (WorkbenchDataAccessException e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchServiceException(e);
		}

	}

}
