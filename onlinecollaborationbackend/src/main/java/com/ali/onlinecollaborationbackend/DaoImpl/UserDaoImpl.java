package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.User;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> listUsers() {

		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(User.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserByUsername(String name) {
		Query q = sessionFactory.getCurrentSession().createQuery("From User where userName=:userName");
		q.setParameter("userName", name);
		User user = (User) q.getSingleResult();

		return user;

	}

	@Override
	public boolean userAuthenticate(String username, String password) {
		User user=getUserByUsername(username);
		boolean result=false;
		if(user!=null)
		{
			if(password.equals(user.getPassword()))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		return result;
	}

}
