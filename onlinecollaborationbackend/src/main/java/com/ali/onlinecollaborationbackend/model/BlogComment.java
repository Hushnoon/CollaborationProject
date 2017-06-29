package com.ali.onlinecollaborationbackend.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(schema="HUSH")
public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int blogCommentId;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JsonBackReference(value="BlogAndComment-movement")
	private Blog blog;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;
	private String comments;
	private Date commentDate;
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getBlogCommentId() {
		return blogCommentId;
	}
	public void setBlogCommentId(int blogCommentId) {
		this.blogCommentId = blogCommentId;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
