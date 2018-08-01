/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
public interface HashtagService {
    
    public Hashtag insertHashtag(Hashtag hashtag);
    
    public Hashtag getHashtagById(int hashtagId);
    
    public Hashtag getHashtagByName(String name);
    
    public List<Hashtag> getAllHashtags();
    
    public List<Hashtag> getHashtagsByBlog(Blog blog);
    
    public void removeHashtag(Hashtag hashtag);
    
    public boolean hashtagExists(Hashtag hashtag);
    
    public String getHashtagLinkedBody(Blog blog);
    
    public List<Hashtag> getHashtagsFromText(String text);
    
    public void removeUnusedHashtags();
    
    public void removeBlogFromHashtag(Hashtag hashtag);
    
    public void processNewContentHashtags(Blog blog);
    
    public void processDeletedContentHashtags(Blog blog);
    
    public HomeViewModel getHomeViewModel(int hashId);
}
