package com.avalon.workbench.services.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public boolean AuthenticateUser(String uname,String pass);
}
