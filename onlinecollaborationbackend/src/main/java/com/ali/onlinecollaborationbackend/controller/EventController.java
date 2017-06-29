package com.ali.onlinecollaborationbackend.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ali.onlinecollaborationbackend.Dao.EventDao;
import com.ali.onlinecollaborationbackend.model.Event;

@RestController
public class EventController {

	@Autowired
	EventDao eventDao;
	
	@PostMapping("/addevent")
	public ResponseEntity<Event> addEvent(@RequestBody Event event) {
		
		event.setCreatedOn(new Date(Calendar.getInstance().getTime().getTime()));
		eventDao.addEvent(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

}
