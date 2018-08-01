/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.service.StaticPageService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/admin_home_static_pages"})
public class AdminHomeStaticPagesController {
    
    StaticPageService staticPageService;
    
    @Inject
    public AdminHomeStaticPagesController(StaticPageService staticPageService) {
        
        this.staticPageService = staticPageService;
        
    }
    
    @RequestMapping(value="/displayAdminHomeStaticPages", method=RequestMethod.GET)
    public String displayAdminHomeStaticPages(Model model) {
        
        return "admin_home_static_pages"; //this means I want to return to the admin_home_static_pages.jsp
    }
    
}
