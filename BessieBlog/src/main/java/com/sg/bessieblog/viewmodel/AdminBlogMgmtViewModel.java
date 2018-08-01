/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.viewmodel;

import com.sg.bessieblog.dto.Blog;
import java.util.List;

/**
 *
 * @author matt
 */
public class AdminBlogMgmtViewModel {
    private List<Blog> publishedBlogs;
    private List<Blog> pendingBlogs;
    private List<Blog> expiredBlogs;

    public List<Blog> getPublishedBlogs() {
        return publishedBlogs;
    }

    public void setPublishedBlogs(List<Blog> publishedBlogs) {
        this.publishedBlogs = publishedBlogs;
    }

    public List<Blog> getPendingBlogs() {
        return pendingBlogs;
    }

    public void setPendingBlogs(List<Blog> pendingBlogs) {
        this.pendingBlogs = pendingBlogs;
    }

    public List<Blog> getExpiredBlogs() {
        return expiredBlogs;
    }

    public void setExpiredBlogs(List<Blog> expiredBlogs) {
        this.expiredBlogs = expiredBlogs;
    }
    
    
}
