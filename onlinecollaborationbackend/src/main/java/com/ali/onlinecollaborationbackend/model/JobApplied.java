package com.ali.onlinecollaborationbackend.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class JobApplied {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int jobAppliedId;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Job job;
	@OneToOne(cascade=CascadeType.PERSIST)
	private User user;
	private Date appliedDate;
	private String remarks;
	private String status;
	public int getJobAppliedId() {
		return jobAppliedId;
	}
	public void setJobAppliedId(int jobAppliedId) {
		this.jobAppliedId = jobAppliedId;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
