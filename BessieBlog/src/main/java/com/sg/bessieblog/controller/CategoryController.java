/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.commandmodel.CategoryFormCommandModel;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.service.CategoryService;
import com.sg.bessieblog.viewmodel.CategoryPageViewModel;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping({"/categories"})
public class CategoryController {

    private final CategoryService categoryService;

    @Inject
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayCategories(Model model) {
        CategoryPageViewModel cpvm = categoryService.getCategoryPageViewModel();
        model.addAttribute("cpvm", cpvm);
        return "categories";
    }

    @RequestMapping(value = {"/categoryList"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategoryList() {
	return categoryService.getAllCategories();
    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category createCategory(@RequestBody Category category) {
        return categoryService.insertCategory(category);
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") int id) {        
        categoryService.removeCategory(id);
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public void updateCategory(@PathVariable("id") int id,
	    @RequestBody CategoryFormCommandModel cfcm) {
	cfcm.setCategoryId(id);
	categoryService.updateCategory(cfcm);
    }
}
    
//    @RequestMapping(value = "/displayEditCategory", method = RequestMethod.GET)
//    public String displayEditCategory(Model model, @RequestParam int categoryToEdit) {
//
//        model.addAttribute("categoryToEdit", categoryToEdit);
//        return "edit_category";
//
//    }

    
//    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
//    public String createCategory(Model model, CategoryFormCommandModel cfcm) {
//
//        return "categories";
//    }
//
//    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
//    @ResponseBody
//    public Category getCategory(@PathVariable("id") int id) {
//        return categoryService.getCategoryById(id);
//    }
//
//    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
//        category.setId(id);
//        categoryService.updateCategory(category);
//    }
//
//    @RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
//    @ResponseBody
//    public Category updateCategory(@RequestParam(value = "id") int id, Model model, CategoryFormCommandModel cfcm) {
//
//        return categoryService.getCategoryById(id);
//    }
//
//    public String removeCategory(HttpServletRequest request, Model model) {
//        return "category";
//    }