package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobApply {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int applyid;
	
	private int jobid;
	
	private String applieduser;

	public int getApplyid() {
		return applyid;
	}

	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public String getApplieduser() {
		return applieduser;
	}

	public void setApplieduser(String applieduser) {
		this.applieduser = applieduser;
	}
	
	
}
