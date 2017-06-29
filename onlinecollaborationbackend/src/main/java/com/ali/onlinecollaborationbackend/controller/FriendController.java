package com.ali.onlinecollaborationbackend.controller;

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

import com.ali.onlinecollaborationbackend.Dao.FriendDao;
import com.ali.onlinecollaborationbackend.model.Friend;
import com.ali.onlinecollaborationbackend.model.User;

@RestController
public class FriendController {

	@Autowired
	FriendDao friendDao;

	@PostMapping("/sendRequest")
	public ResponseEntity<Void> addFriendRequest(@RequestBody Friend friend) {
		User user = friend.getUser();
		friend.setStatus("new");
		friend.setInitiator(1);
		if (friendDao.addFriend(friend)) {
			friend.setUser(friend.getFriend());
			friend.setFriend(user);
			friend.setInitiator(0);
			if (friendDao.addFriend(friend))
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		} else
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	// for my sent request
	@GetMapping("/getSentRequests/{uid}")
	public ResponseEntity<List<Friend>> getMyRequest(@PathVariable("uid") int id) {
		List<Friend> newRequests = friendDao.getRequestsByUserId(id);
		if (newRequests != null)
			return new ResponseEntity<List<Friend>>(newRequests, HttpStatus.OK);
		else
			return new ResponseEntity<List<Friend>>(newRequests, HttpStatus.NOT_FOUND);
	}

	// for request for me
	@GetMapping("/getMyRequests/{uid}")
	public ResponseEntity<List<Friend>> getFriendRequest(@PathVariable("uid") int id) {
		List<Friend> newRequests = friendDao.getRequestsByFriendId(id);
		if (newRequests != null)
			return new ResponseEntity<List<Friend>>(newRequests, HttpStatus.OK);
		else
			return new ResponseEntity<List<Friend>>(newRequests, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateRequest")
	public ResponseEntity<Void> updateFriendRequests(@RequestBody Friend friend) {
		if (friendDao.updateFriend(friend)) {
			Friend f = friendDao.getByFriend_userId(friend.getFriend().getUserId(), friend.getUser().getUserId());
			f.setStatus(friend.getStatus());
			System.out.println("friend's status" + f.getStatus());
			System.out.println(f.getFriendId());
			System.out.println(friend.getFriendId());
			if (friendDao.updateFriend(f)) {
				System.out.println("success");
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				System.out.println("failed1");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			System.out.println("failed2");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{fid}")
	public ResponseEntity<Void> deleteFriendRequests(@PathVariable("fid") int id) {
		Friend friend = friendDao.getById(id);
		Friend f = friendDao.getByFriend_userId(friend.getFriend().getUserId(), friend.getUser().getUserId());
		if (friendDao.deleteFriend(friend)) {
			if (friendDao.deleteFriend(f))
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getFriends/{uid}")
	public ResponseEntity<List<Friend>> getFriendList(@PathVariable("uid") int id) {
		List<Friend> newRequests = friendDao.getByUserId(id);
		if (newRequests != null)
			return new ResponseEntity<List<Friend>>(newRequests, HttpStatus.OK);
		else
			return new ResponseEntity<List<Friend>>(newRequests, HttpStatus.NOT_FOUND);
	}

}
