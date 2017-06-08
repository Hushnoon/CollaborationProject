package com.ali.onlinecollaborationbackend.Dao;

import java.util.List;

import com.ali.onlinecollaborationbackend.model.BlogComment;

public interface BlogCommentDao {
	public List<BlogComment> commentsList();

	public boolean saveComment(BlogComment comment);
}
