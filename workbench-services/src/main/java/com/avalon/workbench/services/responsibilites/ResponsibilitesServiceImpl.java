package com.avalon.workbench.services.responsibilites;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.repository.responsibilites.ResponsibilitesRepository;
import com.avalon.workbench.repository.user.UserRepositoryImpl;

@Service("ResponsibilitesServiceImpl")
public class ResponsibilitesServiceImpl implements ResponsibilitesService {
	protected static final Logger LOG_R = Logger
			.getLogger(UserRepositoryImpl.class);

	@Autowired
	@Qualifier("ResponsibilitesRepositoryImpl")
	private ResponsibilitesRepository responsibilitesRepository;

	public List<Responsibilites> getResonsibilites(String uname) {
		// TODO Auto-generated method stub
		try {
			List<Responsibilites> responsibilites = responsibilitesRepository
					.getResonsibilites(uname);
			if (responsibilites != null && !responsibilites.isEmpty()) {
				return responsibilites;
			}
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
		}
		return null;
	}

}
