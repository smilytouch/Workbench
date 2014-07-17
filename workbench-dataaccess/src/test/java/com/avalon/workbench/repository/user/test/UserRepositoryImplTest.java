package com.avalon.workbench.repository.user.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.avalon.workbench.entities.user.User;
import com.avalon.workbench.repository.test.AbstractWorkbenchDataAccessTest;
import com.avalon.workbench.repository.user.UserRepository;
import com.avalon.workbench.repository.user.UserRepositoryImpl;

public class UserRepositoryImplTest extends AbstractWorkbenchDataAccessTest{
	protected static final Logger LOG_R = Logger
			.getLogger(UserRepositoryImpl.class);
	@Autowired
	@Qualifier(value = "UserRepositoryImpl")
	UserRepository repository;

	@Test
	public void test_getUser_success() throws Exception {
		LOG_R.info("repository="+repository);
		User users = repository.getUser("AALLEN");
		LOG_R.info("name==="+users.getUSER_NAME()+"id=="+users.getUSER_ID());
		Assert.assertTrue(users.getUSER_NAME().equalsIgnoreCase("AALLEN"));
	}
}
