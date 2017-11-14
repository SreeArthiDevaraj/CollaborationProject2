package com.niit.dao;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.JobApply;

public interface JobDao {
	void addJob(Job job);
   List<Job>getAllJobs();
Job getJob(int jobId);
void applyJob(JobApply jobapp);
JobApply isApplied(int jobId, String username);
}
