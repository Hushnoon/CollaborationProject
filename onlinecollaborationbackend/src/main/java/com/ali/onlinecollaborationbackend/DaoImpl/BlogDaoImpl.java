package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.BlogDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.Blog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;
	public List<Blog> getAllBlog() {

		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}

	public boolean addBlog(Blog blog) {
		try {
			System.out.println(blog.getPostDate());
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Blog getById(int blogId) {
		return sessionFactory.getCurrentSession().get(Blog.class, blogId);
	}

	public List<Blog> getByUserId(int userId) {
		return sessionFactory.getCurrentSession().createQuery("from Blog where user.userId=" + userDao.getUserById(userId).getUserId()).list();

	}
}
