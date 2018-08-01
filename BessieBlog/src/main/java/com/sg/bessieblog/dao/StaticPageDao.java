/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.time.LocalDateTime;

import java.util.List;

import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;

/**
 *
 * @author main
 */
public interface StaticPageDao {
    
    public StaticPage insertStaticPage(StaticPage page);
    
    public StaticPage getStaticPageById(int id);
    
    public List<StaticPage> getAllStaticPages();
    
    public void removeStaticPage(StaticPage page);
    
    public void updateStaticPage(StaticPage page);
    
    //public List<StaticPage> getStaticPagesByStatus(String status);
    
    //public List<StaticPage> getStaticPagesByDate(LocalDateTime date);
    
    //public List<StaticPage> getStaticPagesByCategory(Category category);
    
    public List<StaticPage> getStaticPagesByUserId(int userId);
    
    // is this method necessary?
    //public List<StaticPage> getStaticPagesByHashtag(Hashtag tag);
    
    public List<StaticPage> getAllActiveStaticPages();
    
    public List<StaticPage> getAllPendingStaticPages();

    public List<StaticPage> getAllExpiredStaticPages();
    
    public List<StaticPage> getAllActiveStaticPagesByUserId(int id);

    public List<StaticPage> getAllPendingStaticPagesByUserId(int id);

    public List<StaticPage> getAllExpiredStaticPagesByUserId(int id);
}
