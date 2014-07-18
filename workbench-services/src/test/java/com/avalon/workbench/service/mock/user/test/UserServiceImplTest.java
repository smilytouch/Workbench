package com.avalon.workbench.service.mock.user.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.avalon.workbench.beans.user.User;
import com.avalon.workbench.repository.user.UserRepository;
import com.avalon.workbench.services.test.AbstractWorkbenchServiceTest;
import com.avalon.workbench.services.user.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends AbstractWorkbenchServiceTest {

	@InjectMocks
	UserServiceImpl service;
	
	@Mock
	private UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_AuthenticateUser_Success()
			throws Exception {
		User user=new User();
		user.setUSER_NAME("test1");
		Mockito.when(userRepository.getUser(Mockito.anyString())).thenReturn(user);
		boolean value=service.AuthenticateUser("test1");
		Assert.assertTrue(value==(true));
	}
}
