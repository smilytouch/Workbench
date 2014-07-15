package com.avalon.workbench.entities.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fnd_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 8742033608551005976L;

	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEE_ID")
	private Long empId;
	
	@Column(name = "START_DATE", updatable = false, nullable = false)
	private Date start_Date;

	@Column(name = "END_DATE", updatable = false, nullable = false)
	private Date end_Date;

	public Date getEffective_Start_Date() {
		return start_Date;
	}

	public void setEffective_Start_Date(Date effective_Start_Date) {
		this.start_Date = effective_Start_Date;
	}

	public Date getEffective_End_Date() {
		return end_Date;
	}

	public void setEffective_End_Date(Date effective_End_Date) {
		this.end_Date = effective_End_Date;
	}

	public Long getId() {
		return empId;
	}

	public void setId(Long id) {
		this.empId = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "USER_NAME", updatable = false, nullable = false)
	private String uname;
	
	@Column(name = "pass", updatable = false, nullable = false)
	private String pass;
}
