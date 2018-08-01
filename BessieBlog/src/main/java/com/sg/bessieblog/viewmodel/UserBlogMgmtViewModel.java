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
public class UserBlogMgmtViewModel {
    private List<Blog> publishedBlogsByUser;
    private List<Blog> pendingBlogsByUser;
    private List<Blog> expiredBlogsByUser;

    public List<Blog> getPublishedBlogsByUser() {
        return publishedBlogsByUser;
    }

    public void setPublishedBlogsByUser(List<Blog> publishedBlogsByUser) {
        this.publishedBlogsByUser = publishedBlogsByUser;
    }

    public List<Blog> getPendingBlogsByUser() {
        return pendingBlogsByUser;
    }

    public void setPendingBlogsByUser(List<Blog> pendingBlogsByUser) {
        this.pendingBlogsByUser = pendingBlogsByUser;
    }

    public List<Blog> getExpiredBlogsByUser() {
        return expiredBlogsByUser;
    }

    public void setExpiredBlogsByUser(List<Blog> expiredBlogsByUser) {
        this.expiredBlogsByUser = expiredBlogsByUser;
    }
    
    
}
