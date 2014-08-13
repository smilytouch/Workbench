package com.avalon.workbench.repository.concurrentReport;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.concurrntReport.Parameters;
import com.avalon.workbench.beans.user.User;
import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;

@Repository("concurrentReportParamsRepositoryImpl")
public class concurrentReportParamsRepositoryImpl implements
		concurrentReportParamsRepository {
	protected static final Logger LOG_R = Logger
			.getLogger(concurrentReportParamsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Parameters> getParams(String progName)
			throws WorkbenchDataAccessException {
		try {
			String sql = "select fdfcuv.form_left_prompt prompt from   "
					+ "fnd_concurrent_programs fcp, fnd_concurrent_programs_tl fcpl   , "
					+ "fnd_descr_flex_col_usage_vl fdfcuv   , fnd_application_vl fav "
					+ "where  fcp.concurrent_program_id = fcpl.concurrent_program_id and    "
					+ "fcpl.user_concurrent_program_name ='"
					+ progName
					+ "' and    fav.application_id=fcp.application_id and    fcpl.language = 'US' and    fdfcuv.descriptive_flexfield_name = '$SRS$.' || fcp.concurrent_program_name";
			LOG_R.info("sql===" + sql);
			BeanPropertyRowMapper rm = new BeanPropertyRowMapper(
					Parameters.class);
			List<Parameters> result = jdbcTemplate.query(sql, rm);
			return result;
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchDataAccessException(e);
		}
	}

}
