package com.ali.onlinecollaborationbackend.DaoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ali.onlinecollaborationbackend.Dao.BlogCommentDao;
import com.ali.onlinecollaborationbackend.model.BlogComment;

@Repository("blogCommentDao")
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<BlogComment> commentsList() {

		return sessionFactory.getCurrentSession().createQuery("from BlogComment").list();
	}

	@Override
	public boolean saveComment(BlogComment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
