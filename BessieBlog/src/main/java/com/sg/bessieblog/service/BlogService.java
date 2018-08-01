/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.BlogFormCommandModel;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.AdminBlogMgmtViewModel;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import com.sg.bessieblog.viewmodel.CreateBlogViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import com.sg.bessieblog.viewmodel.UserBlogMgmtViewModel;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author matt
 */
public interface BlogService {

    public Blog insertBlog(BlogFormCommandModel bfcm);

    public Blog getBlogById(int blogId);

    public List<Blog> getAllBlogs();

    public void removeBlog(Blog blog);

    public void updateBlog(BlogFormCommandModel bfcm);

    public List<Blog> getBlogsByStatus(String status);

    public List<Blog> getBlogsByDate(LocalDateTime date);

    public List<Blog> getBlogsByCategoryId(int categoryId);

    public List<Blog> getBlogsByUserId(User user);

    public List<Blog> getBlogsByHashtag(Hashtag hashtag);

    public List<Blog> getAllActiveBlogs();

    public List<Blog> getAllPendingBlogs();

    public BlogFormCommandModel getBlogFormCommandModel(Blog blog);

    public HomeViewModel getHomeViewModel();
    public HomeViewModel getHomeViewModel(List<Blog> blogs);
    //public HomeViewModel getHomeViewModel(int id);
    
    public BlogViewModel getBlogViewModel(Blog blog);

    public CreateBlogViewModel getCreateBlogViewModel();

    public UserBlogMgmtViewModel getUserBlogMgmtViewModelByUser(User user);

    public Blog eagerlyGetBlogById(int blogId);

    public void removeHashtagFromBlog(Blog blog);

    public List<Blog> getAllPendingBlogPostsByUser(User user);

    public List<Blog> getAllActiveBloggPostsByUser(User user);

    public List<Blog> getAllExpiredBlogPostsByUser(User user);
    
    public List<Blog> getAllExpiredBlogs();
    
     public AdminBlogMgmtViewModel getAdminBlogMgmtViewModel();

    

}
