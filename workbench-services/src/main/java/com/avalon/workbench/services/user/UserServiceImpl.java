package com.avalon.workbench.services.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avalon.workbench.entities.user.User;
import com.avalon.workbench.repository.user.UserRepository;
import com.avalon.workbench.repository.user.UserRepositoryImpl;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	protected static final Logger LOG_R = Logger
			.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	@Qualifier(value = "UserRepositoryImpl")
	UserRepository repository;
	
	public boolean AuthenticateUser(String uname) {
		// TODO Auto-generated method stub
		try {
			User users = repository.getUser(uname);
			LOG_R.info("Inside AuthenticateUser...user="+users);
			if (users.getUSER_NAME().equals(uname)) {
				return true;
			}
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
		}
		return false;
	}

}
