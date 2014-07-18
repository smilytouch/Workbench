package com.avalon.workbench.repository.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.avalon.workbench.beans.responsibilites.Responsibilites;
import com.avalon.workbench.beans.user.User;

@Repository("UserRepositoryImpl")
public class UserRepositoryImpl implements UserRepository {
	protected static final Logger LOG_R = Logger
			.getLogger(UserRepositoryImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getUser(String uname) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT user_id, user_name FROM fnd_user fu, per_all_people_f papf "
					+ "WHERE papf.person_id = fu.employee_id "
					+ "AND fu.user_name='AALLEN' "
					+ "AND SYSDATE BETWEEN fu.start_date "
					+ "AND NVL (fu.end_date, SYSDATE) "
					+ "AND SYSDATE BETWEEN papf.effective_start_date "
					+ "AND NVL (papf.effective_end_date, SYSDATE ) ORDER BY 2";

			BeanPropertyRowMapper rm = new BeanPropertyRowMapper(User.class);
			List<User> result = jdbcTemplate.query(sql, rm);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
		}
		return null;
	}
}
