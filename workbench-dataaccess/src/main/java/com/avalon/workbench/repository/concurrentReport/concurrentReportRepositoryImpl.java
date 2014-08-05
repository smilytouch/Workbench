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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.avalon.workbench.repository.exception.WorkbenchDataAccessException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Repository("concurrentReportRepositoryImpl")
public class concurrentReportRepositoryImpl implements
		concurrentReportRepository {
	protected static final Logger LOG_R = Logger
			.getLogger(concurrentReportRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void runConcurrentProgram(final String respName, final String uname, final String progName) throws WorkbenchDataAccessException {
		try {
			CallableStatementCreator callableStatementCreator=new CallableStatementCreator() {
				
				@Override
				public CallableStatement createCallableStatement(Connection con)
						throws SQLException {
					CallableStatement cs=con.prepareCall("{call Concurrent_Prog_Exec(?,?,?,?,?,?,?,?,?,?)}");
					cs.setString(1, respName);
					cs.setString(2, uname);
					cs.setString(3, "PO");
					cs.setString(4, "EMPLOYEE_INFORMATION");
					cs.setString(5, "en");
					cs.setString(6, "US");
					cs.setString(7, "PDF");
					cs.setString(8, "PO");
					cs.setString(9, progName);
					cs.setString(10, "1010");
					return cs;
				}
			};
			List params=new ArrayList();
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			params.add(new SqlParameter(Types.VARCHAR));
			jdbcTemplate.call(callableStatementCreator, params);
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
			throw new WorkbenchDataAccessException(e);
		}
	}

	@Override
	public void getConcurrentReport() throws WorkbenchDataAccessException {
		String hostname = "192.168.100.100";
		String username = "root";
		String password = "redhat";
		String copyFrom = "/oraAS/oracle/VIS/inst/apps/VIS_apps/logs/appl/conc/out/EMPLOYEE_INFORMATION_5900869_1.PDF";
        String copyTo = "F:/san.pdf"; 
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
