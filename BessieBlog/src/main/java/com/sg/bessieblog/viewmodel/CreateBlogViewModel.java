/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.viewmodel;

import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author Nick
 */
public class CreateBlogViewModel {
    
    private List<Category> categoryList;
    private List<StaticPage> staticPageList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<StaticPage> getStaticPageList() {
        return staticPageList;
    }

    public void setStaticPageList(List<StaticPage> staticPageList) {
        this.staticPageList = staticPageList;
    }
    

    
}
