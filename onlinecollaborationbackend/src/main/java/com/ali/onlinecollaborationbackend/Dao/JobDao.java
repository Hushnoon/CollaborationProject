package com.ali.onlinecollaborationbackend.Dao;


import java.util.List;

import com.ali.onlinecollaborationbackend.model.Job;

public interface JobDao {
	public List<Job> getAllJob();

	public boolean addJob(Job job);

	public boolean updateJob(Job job);

	public boolean deleteJob(Job job);

	public Job getById(int jobId);

	public List<Job> getByUserId(int userId);
}
