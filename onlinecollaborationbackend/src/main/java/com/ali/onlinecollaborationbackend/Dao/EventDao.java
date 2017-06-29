package com.ali.onlinecollaborationbackend.Dao;


import java.util.List;

import com.ali.onlinecollaborationbackend.model.Event;

public interface EventDao {
	public List<Event> getAllEvent();

	public boolean addEvent(Event event);

	public boolean updateEvent(Event event);

	public boolean deleteEvent(Event event);

	public Event getById(int eventId);

	public List<Event> getByUserId(int userId);
}
