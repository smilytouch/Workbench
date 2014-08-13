package com.avalon.workbench.repository.concurrentReport;

import org.springframework.stereotype.Repository;

import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;

@Repository
public interface concurrentReportRepository {
	public String runConcurrentProgram(String respName, String uname, String progName) throws WorkbenchDataAccessException;
	public void getConcurrentReport(String progName) throws WorkbenchDataAccessException;
}
