package com.avalon.workbench.repository.concurrentReport;

import org.springframework.stereotype.Repository;

import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;

@Repository
public interface concurrentReportRepository {
	public void runConcurrentProgram(String respName, String uname, String progName) throws WorkbenchDataAccessException;
	public void getConcurrentReport() throws WorkbenchDataAccessException;
}
