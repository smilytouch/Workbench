package com.avalon.workbench.services.concurrentReport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avalon.workbench.repository.concurrentReport.concurrentReportRepository;
import com.avalon.workbench.repository.concurrentReport.concurrentReportRepositoryImpl;
import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;
import com.avalon.workbench.services.exception.WorkbenchServiceException;

@Service("ConcurrentReportServiceImpl")
public class ConcurrentReportServiceImpl implements ConcurrentReportService {
	protected static final Logger LOG_R = Logger
			.getLogger(ConcurrentReportServiceImpl.class);

	@Autowired()
	@Qualifier(value = "concurrentReportRepositoryImpl")
	concurrentReportRepository concurrentReportRepository;

	public void runConcurrentProgram(String respName, String uname,
			String progName) throws WorkbenchServiceException {
		try {
			concurrentReportRepository.runConcurrentProgram(respName, uname,
					progName);
		} catch (WorkbenchDataAccessException e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchServiceException(e);
		}

	}

	public void getConcurrentReport() throws WorkbenchServiceException {
		try {
			concurrentReportRepository.getConcurrentReport();
		} catch (WorkbenchDataAccessException e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchServiceException(e);
		}
	}

}
