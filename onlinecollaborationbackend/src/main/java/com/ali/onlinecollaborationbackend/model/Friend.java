package com.ali.onlinecollaborationbackend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int friendId;
	@OneToOne(cascade=CascadeType.PERSIST)
	private User user;
	@OneToOne(cascade=CascadeType.PERSIST)
	private User friend;
	private String status;
	private int initiator;
	//---------------------------------------------------
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getInitiator() {
		return initiator;
	}
	public void setInitiator(int initiator) {
		this.initiator = initiator;
	}
	
	
}
