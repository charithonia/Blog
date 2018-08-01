/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.util.List;

import com.sg.bessieblog.dto.Category;

/**
 *
 * @author main
 */
public interface CategoryDao {
    
    public Category insertCategory(Category category);
    
    public Category getCategoryById(int id);
    
    public List<Category> getAllCategories();
    
    public void removeCategory(Category category);
    
    public void updateCategory(Category category);
}
