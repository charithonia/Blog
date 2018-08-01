/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.util.List;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Hashtag;

/**
 *
 * @author main
 */
public interface HashtagDao {
    
    public Hashtag insertHashtag(Hashtag hashtag);
    
    public Hashtag getHashtagById(int id);
    
    public Hashtag getHashtagByName(String name);
    
    public List<Hashtag> getAllHashtags();
    
    public List<Hashtag> getHashtagsByBlog(Blog blog);
    
    public void removeHashtag(Hashtag hashtag);
    
    public boolean hashtagExists(Hashtag hashtag);
}
