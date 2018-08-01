/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.service.BlogService;
import com.sg.bessieblog.service.StaticPageService;
import com.sg.bessieblog.service.UserService;
import com.sg.bessieblog.viewmodel.AdminBlogMgmtViewModel;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Nick
 */
@Controller
@RequestMapping({"/admin_blog_mgmt"})
public class AdminBlogMgmtController {
    
    BlogService bService;
    StaticPageService spService;
    UserService uService;
    
    @Inject
    public AdminBlogMgmtController(BlogService bService, UserService uService, StaticPageService spService){
        this.bService = bService;
        this.uService = uService;
        this.spService = spService;
    }
    
    @RequestMapping(value= {"/admin_blog_view"}, method= RequestMethod.GET)
    @ResponseBody
    public AdminBlogMgmtViewModel getAdminBlogMgmtViewModel(){
        return bService.getAdminBlogMgmtViewModel();
    }
    
}
