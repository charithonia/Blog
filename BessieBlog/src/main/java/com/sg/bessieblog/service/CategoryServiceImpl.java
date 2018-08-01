/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.CategoryFormCommandModel;
import com.sg.bessieblog.dao.CategoryDao;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import com.sg.bessieblog.viewmodel.CategoryPageViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    private StaticPageService staticPageService;
    private BlogService blogService;
    
    public void setCategoryDao(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }
    
    public void setStaticPageService(StaticPageService staticPageService) {
	this.staticPageService = staticPageService;
    }
    
    public void setBlogService(BlogService blogService){
        this.blogService = blogService;
    }
    
    @Override
    public Category insertCategory(Category category) {
        categoryDao.insertCategory(category);
	return category;
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public void removeCategory(Category category) {
        categoryDao.removeCategory(category);
    }
    
    @Override
    public void removeCategory(int id) {
	Category category = getCategoryById(id);
	removeCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }
    
    @Override
    public void updateCategory(CategoryFormCommandModel cfcm) {
	Category category = new Category();
	category.setId(cfcm.getCategoryId());
	category.setName(cfcm.getName());
	
	updateCategory(category);
    }

    @Override
    public CategoryPageViewModel getCategoryPageViewModel() {
        CategoryPageViewModel cpvm = new CategoryPageViewModel();
	
	List<Category> categories = categoryDao.getAllCategories();
	cpvm.setCategoryList(categories);
	
	List<StaticPage> pages = staticPageService.getAllStaticPages();
	cpvm.setStaticPageList(pages);
	
	return cpvm;
    }

    @Override
    public CategoryFormCommandModel getCategoryFormCommandModel(Category category) {
        CategoryFormCommandModel cfcm = new CategoryFormCommandModel();
	
	int categoryId = category.getId();
	cfcm.setCategoryId(categoryId);
	
	String name = category.getName();
	cfcm.setName(name);
	
	return cfcm;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public HomeViewModel getHomeViewModel(int categoryId) {
        HomeViewModel hvm = new HomeViewModel();
        List<Blog> blogList = blogService.getBlogsByCategoryId(categoryId);

        List<BlogViewModel> bvmList = new ArrayList();
    
        for(Blog blog : blogList){
           bvmList.add(blogService.getBlogViewModel(blog));
        };
        
        hvm.setBlogs(bvmList);
        hvm.setCategoryList(getAllCategories());
        hvm.setStaticPages(staticPageService.getAllActiveStaticPages());
        return hvm;
    }
    
}