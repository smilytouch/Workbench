package com.avalon.workbench.repository.concurrentReport.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.avalon.workbench.beans.user.User;
import com.avalon.workbench.repository.concurrentReport.concurrentReportRepository;
import com.avalon.workbench.repository.test.AbstractWorkbenchDataAccessTest;

public class concurrentReportRepositoryImplTest extends
		AbstractWorkbenchDataAccessTest {
	protected static final Logger LOG_R = Logger
			.getLogger(concurrentReportRepositoryImplTest.class);
	@Autowired
	@Qualifier(value = "concurrentReportRepositoryImpl")
	concurrentReportRepository repository;

	@Test
	public void test_runConcurrentProgram_success() throws Exception {
		LOG_R.info("repository="+repository);
		repository.runConcurrentProgram();
		Assert.assertTrue(true);
	}
	@Test
	public void test_getConcurrentReport_success() throws Exception {
		LOG_R.info("repository="+repository);
		repository.getConcurrentReport();
		Assert.assertTrue(true);
	}
}
