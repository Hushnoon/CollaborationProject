package com.ali.onlinecollaborationbackend.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ali.onlinecollaborationbackend.Dao.JobAppliedDao;
import com.ali.onlinecollaborationbackend.Dao.JobDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.Job;
import com.ali.onlinecollaborationbackend.model.JobApplied;

@RestController
public class JobController {
	@Autowired
	JobDao jobDao;
	@Autowired
	UserDao userDao;
	@Autowired
	JobAppliedDao jobAppliedDao;

	//------Post a job
	@PostMapping("/postjob")
	public ResponseEntity<Void> create(@RequestBody Job job) {
		job.setPostedOn(new Date(Calendar.getInstance().getTime().getTime()));
		if (jobDao.addJob(job)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}

	//------view all jobs
	@GetMapping("/viewalljob")
	public ResponseEntity<List<Job>> list() {
		List<Job> getalljobs = jobDao.getAllJob();
		return new ResponseEntity<List<Job>>(getalljobs, HttpStatus.OK);
	}

	//------get a job detail by id
	@GetMapping("/getjobid/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable("jobId") int jobId) {
		Job job = jobDao.getById(jobId);
		if (job == null) {
			Job blg = new Job();
			return new ResponseEntity<Job>(blg, HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
	}
	
	//------apply for a job
	@PostMapping("/apply/job")
	public ResponseEntity<Void> apply(@RequestBody JobApplied jobApplied) {
		jobApplied.setAppliedDate(new Date(Calendar.getInstance().getTime().getTime()));
		if (jobAppliedDao.addJobApplied(jobApplied)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
	//------ view applied jobs by user id
	@GetMapping("/getjob/applied/userid/{userId}")
	public ResponseEntity<List<JobApplied>> getJobAplliedByUserID(@PathVariable("userId") int userId) {
		List<JobApplied> jobs = jobAppliedDao.getByUserId(userId);

		return new ResponseEntity<List<JobApplied>>(jobs, HttpStatus.OK);

	}

	//------view applied candidates
	@GetMapping("/view/candidates")
	public ResponseEntity<List<JobApplied>> viewApplied() {
		List<JobApplied> getallcandidates = jobAppliedDao.getAllJobApplied();
		return new ResponseEntity<List<JobApplied>>(getallcandidates, HttpStatus.OK);
	}
	
	//------ view applied jobs by job id
		@GetMapping("/getjob/applied/job/{jobId}")
		public ResponseEntity<List<JobApplied>> getJobAplliedByJobID(@PathVariable("jobId") int jobId) {
			List<JobApplied> jobs = jobAppliedDao.getByJobId(jobId);

			return new ResponseEntity<List<JobApplied>>(jobs, HttpStatus.OK);

		}
	
}
