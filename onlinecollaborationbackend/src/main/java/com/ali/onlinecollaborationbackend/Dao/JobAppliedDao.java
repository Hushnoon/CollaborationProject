package com.ali.onlinecollaborationbackend.Dao;

import java.util.List;

import com.ali.onlinecollaborationbackend.model.JobApplied;

public interface JobAppliedDao {
	public List<JobApplied> getAllJobApplied();

	public boolean addJobApplied(JobApplied jobApplied);

	public boolean updateJobApplied(JobApplied jobApplied);

	public boolean deleteJobApplied(JobApplied jobApplied);

	public JobApplied getById(int jobAppliedId);

	public List<JobApplied> getByUserId(int userId);

	public List<JobApplied> getByJobId(int jobId);
}
