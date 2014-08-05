package com.avalon.workbench.services.concurrentReport;

import org.springframework.stereotype.Service;

import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;
import com.avalon.workbench.services.exception.WorkbenchServiceException;

@Service
public interface ConcurrentReportService {
	public void runConcurrentProgram(String respName, String uname, String progName) throws WorkbenchServiceException;
	public void getConcurrentReport() throws WorkbenchServiceException;
}
