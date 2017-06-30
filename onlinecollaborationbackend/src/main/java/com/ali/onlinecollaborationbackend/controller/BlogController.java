package com.ali.onlinecollaborationbackend.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ali.onlinecollaborationbackend.Dao.BlogCommentDao;
import com.ali.onlinecollaborationbackend.Dao.BlogDao;
import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.Blog;
import com.ali.onlinecollaborationbackend.model.BlogComment;
import com.ali.onlinecollaborationbackend.model.User;

@RestController
public class BlogController {

	@Autowired
	UserDao userDao;
	@Autowired
	BlogDao blogDao;
	@Autowired
	BlogCommentDao blogCommentDao;

	@PostMapping("/addblog")
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
		blog.setPostDate(new Date(Calendar.getInstance().getTime().getTime()));
		blog.setStatus("new");
		blogDao.addBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@GetMapping("/getallblog")
	public ResponseEntity<List<Blog>> getAllBlog() {
		List<Blog> getallblogs = blogDao.getAllBlog();
		return new ResponseEntity<List<Blog>>(getallblogs, HttpStatus.OK);
	}

	@GetMapping("/getblogid/{blogId}")
	public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") int blogId) {
		Blog blog = blogDao.getById(blogId);
		if (blog == null) {
			Blog blg = new Blog();

			return new ResponseEntity<Blog>(blg, HttpStatus.NOT_FOUND);
		} else {
			String s=new String(blog.getDescription().getBytes());
			System.out.println("Clob data"+s);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteblog/{blogId}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDao.getById(blogId);
		blogDao.deleteBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@GetMapping("/getbloguserid/{userId}")
	public ResponseEntity<List<Blog>> getBlogByUserID(@PathVariable("userId") int userId) {
		List<Blog> blog = blogDao.getByUserId(userId);

		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);

	}

	@PutMapping("/updateblog")
	public ResponseEntity<Void> updateBlog(@RequestBody Blog blog) {
		if (blogDao.updateBlog(blog))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PostMapping("/blog/add/comment")
	public ResponseEntity<Void> addComment(@RequestBody BlogComment blogComment) {
		Blog blog = blogDao.getById(blogComment.getBlog().getBlogId());
		// User user=userDao.getUserById(blogComment.getUser().getUserId());
		blogComment.setBlog(blog);
		// blogComment.setUser(user);
		blogComment.setCommentDate(new Date(Calendar.getInstance().getTime().getTime()));
		if (blogCommentDao.saveComment(blogComment)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
