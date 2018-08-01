/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.service.BlogService;
import com.sg.bessieblog.service.CategoryService;
import com.sg.bessieblog.service.HashtagService;
import com.sg.bessieblog.service.StaticPageService;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    CategoryService categoryService;
    BlogService blogService;
    StaticPageService staticPageService;
    HashtagService hashtagService;

    @Inject
    public HomeController(CategoryService categoryService, BlogService blogService,
            StaticPageService staticPageService, HashtagService hashtagService) {

        this.categoryService = categoryService;
        this.blogService = blogService;
        this.staticPageService = staticPageService;
        this.hashtagService = hashtagService;
    }
    

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        model.addAttribute("hvm", blogService.getHomeViewModel());
        return "home";
    }

    @RequestMapping(value = "/displayOneStaticPage", method = RequestMethod.GET)
    public String displayOneStaticPage(Model model, @RequestParam int staticPageId) {
        model.addAttribute("hvm", staticPageService.getHomeViewModel(staticPageId));
        return "home";
    }

    
    @RequestMapping(value = "/displayActiveBlogsByCategory", method = RequestMethod.GET)
    public String displayActiveBlogsByCategory(Model model, @RequestParam int categoryId){
        model.addAttribute("hvm", categoryService.getHomeViewModel(categoryId));
        return "home";
    }
    
    @RequestMapping(value = "/displayActiveBlogsByHashtag", method = RequestMethod.GET)
    public String displayActiveBlogsByHashtag(Model model, @RequestParam int id){
        model.addAttribute("hvm", hashtagService.getHomeViewModel(id));
        return "home";
    }
}
