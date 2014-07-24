package com.avalon.workbench.repository.concurrentPrograms;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.concurrntPrograms.ConcurrentPrograms;

@Repository
public interface ConcurrentProgramsRepository {
	public List<ConcurrentPrograms> getConcurrentPrograms(String uname,String respNmae);
}
