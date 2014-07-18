package com.avalon.workbench.repository.responsibilites;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.responsibilites.Responsibilites;


@Repository
public interface ResponsibilitesRepository {
	public List<Responsibilites> getResonsibilites(String uname);
}
