package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.JobDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.Job;

@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;
	public List<Job> getAllJob() {

		return sessionFactory.getCurrentSession().createQuery("from Job").list();
	}

	public boolean addJob(Job job) {
		try {
			
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Job getById(int jobId) {
		return sessionFactory.getCurrentSession().get(Job.class, jobId);
	}

	public List<Job> getByUserId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Job where user.userId=" + userDao.getUserById(userId).getUserId()).list();

	}
}
