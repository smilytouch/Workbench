package com.avalon.workbench.service.mock.concurrentPrograms.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.avalon.workbench.beans.concurrntPrograms.ConcurrentPrograms;
import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.repository.concurrentPrograms.ConcurrentProgramsRepository;
import com.avalon.workbench.services.concurrentPrograms.ConcurrentProgramsServiceImpl;
import com.avalon.workbench.services.test.AbstractWorkbenchServiceTest;

@RunWith(MockitoJUnitRunner.class)
public class ConcurrentProgramsRepositoryImplTest extends AbstractWorkbenchServiceTest{
	@InjectMocks
	ConcurrentProgramsServiceImpl service;
	
	@Mock
	private ConcurrentProgramsRepository concurrentProgramsRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_AuthenticateUser_Success()
			throws Exception {
		ConcurrentPrograms concurrentPrograms=new ConcurrentPrograms();
		concurrentPrograms.setUser_name("test1");
		List<ConcurrentPrograms> list=new ArrayList<ConcurrentPrograms>();
		list.add(concurrentPrograms);
		Mockito.when(concurrentProgramsRepository.getConcurrentPrograms(Mockito.anyString(), Mockito.anyString())).thenReturn(list);
		List<ConcurrentPrograms> value=service.getConcurrentPrograms("test1", "Preferences SSWA");
		Assert.assertTrue(value.get(0).getUser_name().equals("test1"));
	}
}
