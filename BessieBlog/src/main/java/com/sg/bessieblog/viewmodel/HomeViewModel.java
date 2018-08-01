/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.viewmodel;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author matt
 */
public class HomeViewModel {
    private List<BlogViewModel> blogs;
    private List<StaticPage> staticPages;
    private List<Category> categoryList;

    private StaticPage pageToDisplay;

    
    
    
    public StaticPage getPageToDisplay() {
        return pageToDisplay;
    }

    public void setPageToDisplay(StaticPage pageToDisplay) {
        this.pageToDisplay = pageToDisplay;
    }
    
    
    
    
    public List<BlogViewModel> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogViewModel> blogs) {
        this.blogs = blogs;
    }

    public List<StaticPage> getStaticPages() {
        return staticPages;
    }

    public void setStaticPages(List<StaticPage> staticPages) {
        this.staticPages = staticPages;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    
}
