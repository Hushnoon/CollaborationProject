package com.ali.onlinecollaborationbackend.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import oracle.sql.CLOB;

@Entity
@Table(schema="HUSH")
public class Blog {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int blogId;
	private String title;
	
	private CLOB description;
	private String status;
	private Date postDate;
	private String reason;
	@Column(name="votes")
	private int likes;
	@Column(name="visited")
	private int views;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "blog")
	@JsonManagedReference(value="BlogAndComment-movement")
	private Set<BlogComment> blogComments=new HashSet<BlogComment>() ;
	
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public CLOB getDescription() {
		return description;
	}
	public void setDescription(CLOB description) {
		this.description = description;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Set<BlogComment> getBlogComments() {
		return blogComments;
	}
	public void setBlogComments(Set<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}
}
