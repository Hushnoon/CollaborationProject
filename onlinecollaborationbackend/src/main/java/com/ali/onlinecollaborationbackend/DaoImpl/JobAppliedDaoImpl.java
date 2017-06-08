package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.JobAppliedDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.JobApplied;

@Repository("jobAppliedDao")
@Transactional
public class JobAppliedDaoImpl implements JobAppliedDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;
	public List<JobApplied> getAllJobApplied() {

		return sessionFactory.getCurrentSession().createQuery("from JobApplied").list();
	}

	public boolean addJobApplied(JobApplied jobApplied) {
		try {
			
			sessionFactory.getCurrentSession().save(jobApplied);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().update(jobApplied);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().delete(jobApplied);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public JobApplied getById(int jobAppliedId) {
		return sessionFactory.getCurrentSession().get(JobApplied.class, jobAppliedId);
	}

	public List<JobApplied> getByUserId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from JobApplied where user.userId=" + userDao.getUserById(userId).getUserId()).list();

	}
	public List<JobApplied> getByJobId(int jobId) {
		return sessionFactory.getCurrentSession().createQuery("from JobApplied where job.jobId=" + jobId).list();

	}
}
