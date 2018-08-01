/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.util.List;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.BlogHashtag;
import com.sg.bessieblog.dto.Hashtag;

/**
 *
 * @author main
 */
public interface BlogHashtagDao {
    
    public BlogHashtag insertBlogHashtag(com.sg.bessieblog.dto.BlogHashtag bh);
    
    public BlogHashtag getBlogHashtagById(int id);
    
    public List<BlogHashtag> getAllBlogHashtags();
    
    public List<BlogHashtag> getBlogHashtagsByBlog(Blog blog);
    
    public void removeBlogHashtag(BlogHashtag bh);
    
    // What do these methods do? If they have to do with amending blogs or
    // hashtags, shouldn't they be in those DAOs or services?
    
    public void removeBlogFromHashtag(Hashtag hashtag);
    
    public void removeHashtagFromBlog(Blog blog);
}
