package com.avalon.workbench.repository.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.avalon.workbench.entities.user.Per_People_f;
import com.avalon.workbench.entities.user.User;

@Repository("UserRepositoryImpl")
public class UserRepositoryImpl implements UserRepository {
	protected static final Logger LOG_R = Logger
			.getLogger(UserRepositoryImpl.class);

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public User getUser(String uname) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			Session session = sessionFactory.openSession();
			LOG_R.info("Session=" + session);
			Query query = session
					.createSQLQuery(
							"SELECT user_id, user_name FROM fnd_user fu, per_all_people_f papf " +
							"WHERE papf.person_id = fu.employee_id " +
							"AND fu.user_name=:name " +
							"AND SYSDATE BETWEEN fu.start_date " +
							"AND NVL (fu.end_date, SYSDATE) " +
							"AND SYSDATE BETWEEN papf.effective_start_date " +
							"AND NVL (papf.effective_end_date, SYSDATE ) ORDER BY 2")
					.addEntity(User.class)
					.addEntity(Per_People_f.class)
					.setParameter("name", uname);
			List result = query.list();
			if (result != null && !result.isEmpty()) {
				user = (User) result.get(0);
			}
		} catch (Exception e) {
			LOG_R.error("Exception occured ::" + e);
		}
		return null;
	}

}
