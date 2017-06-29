package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.FriendDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.Friend;

@Repository("friendDao")
@Transactional
public class FriendDaoImpl implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;
	public List<Friend> getAllFriend() {

		return sessionFactory.getCurrentSession().createQuery("from Friend").list();
	}

	public boolean addFriend(Friend friend) {
		try {
			
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Friend getById(int friendId) {
		return sessionFactory.getCurrentSession().get(Friend.class, friendId);
	}

	public Friend getByFriend_userId(int friendId,int userId) {
		return (Friend)sessionFactory.getCurrentSession().createQuery("from Friend where user.userId=" + friendId +" and friend.userId="+userId).getSingleResult();
	}
	//for confirm friends against user id
	public List<Friend> getByUserId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Friend where user.userId=" + userId+" and status='confirm'").list();

	}
	//for my sent request
	public List<Friend> getRequestsByUserId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Friend where user.userId=" + userId+" and status='new' and initiator=1").list();
	}
	//for request for me
	public List<Friend> getRequestsByFriendId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Friend where user.userId=" + userId+" and status='new' and initiator=0").list();
	}
}
