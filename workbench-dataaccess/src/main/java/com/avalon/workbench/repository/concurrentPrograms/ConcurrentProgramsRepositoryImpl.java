package com.avalon.workbench.repository.concurrentPrograms;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.concurrntPrograms.ConcurrentPrograms;
import com.avalon.workbench.repository.user.UserRepositoryImpl;

@Repository("ConcurrentProgramsRepositoryImpl")
public class ConcurrentProgramsRepositoryImpl implements
		ConcurrentProgramsRepository {
	
	protected static final Logger LOG_R = Logger
			.getLogger(UserRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ConcurrentPrograms> getConcurrentPrograms(String uname,
			String respNmae) {
		// TODO Auto-generated method stub
		return null;
	}

}
