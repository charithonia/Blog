/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.time.LocalDateTime;

import java.util.List;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.User;

/**
 *
 * @author main
 */
public interface BlogDao {

    public Blog insertBlog(Blog blog);

    public Blog getBlogById(int id);

    public List<Blog> getAllBlogs();

    public void removeBlog(Blog blog);

    public void updateBlog(Blog blog);

    public List<Blog> getBlogsByStatus(String status);

    public List<Blog> getBlogsByDate(LocalDateTime date);

    public List<Blog> getBlogsByCategory(Category category);

    public List<Blog> getBlogsByUserId(User user);

    public List<Blog> getBlogsByHashtag(Hashtag hashtag);

    public List<Blog> getAllActiveBlogs();

    public List<Blog> getAllPendingBlogs();

    public List<Blog> getAllExpiredBlogs();

    public List<Blog> getAllActiveBlogsByUserId(User user);

    public List<Blog> getAllPendingBlogsByUserId(User user);

    public List<Blog> getAllExpiredBlogsByUserId(User user);

}
