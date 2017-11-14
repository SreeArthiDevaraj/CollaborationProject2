package com.niit.service;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.JobApply;

public interface JobService {
	void addJob(Job job);
	List<Job>getAllJobs();
	Job getJob(int jobId);
	void applyJob(JobApply jobapp);
	JobApply isApplied(int jobId, String username);
}
