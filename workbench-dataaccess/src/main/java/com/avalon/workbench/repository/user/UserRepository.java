package com.avalon.workbench.repository.user;

import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.beans.user.User;

@Repository
public interface UserRepository {
	public User getUser(String uname);
	
}
