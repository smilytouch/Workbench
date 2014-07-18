package com.avalon.workbench.services.responsibilites;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avalon.workbench.beans.responsibilites.Responsibilites;

@Service
public interface ResponsibilitesService {
	public List<Responsibilites> getResonsibilites(String uname);

}
