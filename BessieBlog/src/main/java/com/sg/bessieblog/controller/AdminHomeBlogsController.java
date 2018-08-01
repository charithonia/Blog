/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.service.BlogService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/admin_home_blogs"})
public class AdminHomeBlogsController {
    
    BlogService blogService;
    
    
    @Inject
    public AdminHomeBlogsController(BlogService blogService) {
        
        this.blogService = blogService;
        
    }
    
    @RequestMapping(value="/displayAdminHomeBlogs", method=RequestMethod.GET)
    public String displayAdminHomeBlogs(Model model) {
        
        return "admin_home_blogs"; //this means I want to return to the admin_home_blogs.jsp
    }
}
