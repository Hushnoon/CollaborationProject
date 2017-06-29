package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.EventDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.Event;

@Repository("eventDao")
@Transactional
public class EventDaoImpl implements EventDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;
	public List<Event> getAllEvent() {

		return sessionFactory.getCurrentSession().createQuery("from Event").list();
	}

	public boolean addEvent(Event event) {
		try {
			
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateEvent(Event event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteEvent(Event event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Event getById(int eventId) {
		return sessionFactory.getCurrentSession().get(Event.class, eventId);
	}

	public List<Event> getByUserId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Event where user.userId=" + userDao.getUserById(userId).getUserId()).list();

	}
}
