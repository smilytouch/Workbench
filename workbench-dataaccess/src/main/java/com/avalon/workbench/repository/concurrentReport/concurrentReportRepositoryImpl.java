package com.avalon.workbench.repository.concurrentReport;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.concurrntReport.Inputs;
import com.avalon.workbench.beans.concurrntReport.Parameters;
import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Repository("concurrentReportRepositoryImpl")
public class ConcurrentReportRepositoryImpl implements
		concurrentReportRepository {
	protected static final Logger LOG_R = Logger
			.getLogger(ConcurrentReportRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String generateConcurrentReport(final String respName,
			final String uname, final String shortName,
			final String concurrentName, final Inputs inputs,
			final ArrayList<String> params) throws WorkbenchDataAccessException {
		try {
			CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection con)
						throws SQLException {
					CallableStatement cs = con
							.prepareCall("{call Concurrent_Prog_Exec(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					cs.setString(1, respName);
					cs.setString(2, uname);
					if (inputs != null) {
						cs.setString(3, inputs.getApplication_Short_Name());
						cs.setString(4, inputs.getTemplate_code());
						cs.setString(5, inputs.getDefault_Language());
						cs.setString(6, inputs.getDefault_Territory());
						cs.setString(7, inputs.getDefault_output_type());
					} else {
						cs.setString(3, null);
						cs.setString(4, null);
						cs.setString(5, null);
						cs.setString(6, null);
						cs.setString(7, null);
					}
					cs.setString(8, shortName);
					cs.setString(9, concurrentName);
					if (params != null) {
						cs.setString(10, params.get(0));
						cs.setString(11, null);
						cs.setString(12, null);
						cs.setString(13, null);
						cs.setString(14, null);
						cs.setString(15, null);
						cs.setString(16, null);
						cs.setString(17, null);
						cs.setString(18, null);
						cs.setString(19, null);
						cs.setString(20, null);
						cs.setString(21, null);
						cs.setString(22, null);
						cs.setString(23, null);
						cs.setString(24, null);
					} else {
						cs.setString(10, null);
						cs.setString(11, null);
						cs.setString(12, null);
						cs.setString(13, null);
						cs.setString(14, null);
						cs.setString(15, null);
						cs.setString(16, null);
						cs.setString(17, null);
						cs.setString(18, null);
						cs.setString(19, null);
						cs.setString(20, null);
						cs.setString(21, null);
						cs.setString(22, null);
						cs.setString(23, null);
						cs.setString(24, null);
					}
					cs.registerOutParameter(25, Types.INTEGER);
					return cs;
				}
			};
			List param = new ArrayList();
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlParameter(Types.VARCHAR));
			param.add(new SqlOutParameter("retVal", Types.INTEGER));
			String reqId = (String) jdbcTemplate
					.call(callableStatementCreator, param).get("retVal")
					.toString();
			if (reqId != null)
				return reqId;
			return "0";
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchDataAccessException(e);
		}
	}

	@Override
	public void getConcurrentReport(String fileName)
			throws WorkbenchDataAccessException {
		String hostname = "192.168.100.100";
		String username = "root";
		String password = "redhat";
		String copyFrom = "/oraAS/oracle/VIS/inst/apps/VIS_apps/logs/appl/conc/out/"
				+ fileName;
		LOG_R.info("Copyfrom===" + copyFrom);
		String copyTo = "F:/" + fileName;
		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession(username, hostname, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			LOG_R.info("inside getConcurrentReport--------Connected");
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.get(copyFrom, copyTo);
			sftpChannel.exit();
			session.disconnect();
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchDataAccessException(e);
		}
	}
}
