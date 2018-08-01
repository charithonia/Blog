/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.CategoryFormCommandModel;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.viewmodel.CategoryPageViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
public interface CategoryService {
    public Category insertCategory(Category category);
    public Category getCategoryById(int categorId);
    public List<Category> getAllCategories();
    public void removeCategory(Category category);
    public void removeCategory(int id);
    public void updateCategory(Category category);
    public void updateCategory(CategoryFormCommandModel cfcm);
    public CategoryPageViewModel getCategoryPageViewModel();
    public CategoryFormCommandModel getCategoryFormCommandModel(Category category);

    public HomeViewModel getHomeViewModel(int categoryId);
}
