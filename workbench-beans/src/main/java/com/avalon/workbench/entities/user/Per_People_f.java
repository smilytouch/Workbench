package com.avalon.workbench.entities.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "per_all_people_f")
public class Per_People_f implements Serializable {

	private static final long serialVersionUID = -4361587106446640933L;
	
	@Id
	@Column(name = "person_id", updatable = false, nullable = false)
	private long personId;

	@Column(name = "EFFECTIVE_START_DATE", updatable = false, nullable = false)
	private Date effective_Start_Date;

	@Column(name = "EFFECTIVE_END_DATE", updatable = false, nullable = false)
	private Date effective_End_Date;

	public Date getEffective_Start_Date() {
		return effective_Start_Date;
	}

	public void setEffective_Start_Date(Date effective_Start_Date) {
		this.effective_Start_Date = effective_Start_Date;
	}

	public Date getEffective_End_Date() {
		return effective_End_Date;
	}

	public void setEffective_End_Date(Date effective_End_Date) {
		this.effective_End_Date = effective_End_Date;
	}
}
