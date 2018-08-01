/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.commandmodel.BlogFormCommandModel;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.service.BlogService;
import com.sg.bessieblog.service.UserService;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import com.sg.bessieblog.viewmodel.CreateBlogViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import com.sg.bessieblog.viewmodel.UserBlogMgmtViewModel;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Nick
 */
@Controller
@RequestMapping({"/blogs"})
public class BlogController {

    BlogService bService;
    UserService uService;

    @Inject
    public BlogController(BlogService bService, UserService uService) {
        this.bService = bService;
        this.uService = uService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayBlogHome(Model model) {
        HomeViewModel hvm =  bService.getHomeViewModel();
        model.addAttribute("hvm", hvm);
        return "blog_mgmt";
    }

    @RequestMapping(value = "/displayBlogDetails", method = RequestMethod.GET)
    public String displayBlogDetails(HttpServletRequest request, Model model) {

        int blogId = Integer.parseInt(request.getParameter("blogId"));
        Blog blog = bService.getBlogById(blogId);
        BlogViewModel bvm = bService.getBlogViewModel(blog);
        model.addAttribute("bvm", bvm);
        return "blog_entry";
    }
    
    @RequestMapping(value= {"/userBlogView/{id}"}, method= RequestMethod.GET)
    @ResponseBody
    public UserBlogMgmtViewModel getUserBlogMgmtViewModel(@PathVariable("id") int id){
        User user = new User();
        user.setId(1);
        return bService.getUserBlogMgmtViewModelByUser(user);
    }
    

    @RequestMapping(value = "/displayEditBlog/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BlogFormCommandModel displayEditBlog(@PathVariable("id") int id) {
        Blog blog = bService.getBlogById(id);
        return bService.getBlogFormCommandModel(blog);
      
    }

    @RequestMapping(value = "/displayCreateBlog", method = RequestMethod.GET)
    public String displayCreateBlog(Model model) {
        CreateBlogViewModel cbvm = bService.getCreateBlogViewModel();
        model.addAttribute("cbvm", cbvm);
        return "create_blog";
    }

    @RequestMapping(value = "/createBlog", method = RequestMethod.POST)
    public String createBlog(@ModelAttribute BlogFormCommandModel bfcm, HttpServletRequest request, Model model, BindingResult result) {
        bService.insertBlog(bfcm);
        return "redirect:/";
    }
    
    

    @RequestMapping(value = "/updateBlog", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBlog(@RequestBody BlogFormCommandModel bfcm) {
        bService.updateBlog(bfcm);
       
    }
    @RequestMapping(value = "/approveBlog/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approveBlog(@PathVariable("id") int id) {
        Blog blog = bService.getBlogById(id);
        blog.setExpirationDate(LocalDateTime.MAX);
        blog.setPublicationDate(LocalDateTime.now());
        
        blog.setIsApproved(true);
        bService.updateBlog(bService.getBlogFormCommandModel(blog));
       
    }
    
 

    @RequestMapping(value = "/deleteBlog/{id}", method = RequestMethod.DELETE)
     @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable("id") int id) {
        
        bService.removeBlog(bService.getBlogById(id));
       
    }
}
