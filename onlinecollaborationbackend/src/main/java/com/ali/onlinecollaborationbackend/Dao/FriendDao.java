package com.ali.onlinecollaborationbackend.Dao;


import java.util.List;

import com.ali.onlinecollaborationbackend.model.Friend;

public interface FriendDao {
	public List<Friend> getAllFriend();

	public boolean addFriend(Friend friend);

	public boolean updateFriend(Friend friend);

	public boolean deleteFriend(Friend friend);

	public Friend getById(int friendId);
	
	public Friend getByFriend_userId(int friendId,int useId);

	public List<Friend> getByUserId(int userId);
	
	public List<Friend> getRequestsByUserId(int userId);
	
	public List<Friend> getRequestsByFriendId(int userId);
}
