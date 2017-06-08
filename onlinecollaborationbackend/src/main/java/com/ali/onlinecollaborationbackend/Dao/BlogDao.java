package com.ali.onlinecollaborationbackend.Dao;


import java.util.List;

import com.ali.onlinecollaborationbackend.model.Blog;

public interface BlogDao {
	public List<Blog> getAllBlog();

	public boolean addBlog(Blog blog);

	public boolean updateBlog(Blog blog);

	public boolean deleteBlog(Blog blog);

	public Blog getById(int blogId);

	public List<Blog> getByUserId(int userId);
}
