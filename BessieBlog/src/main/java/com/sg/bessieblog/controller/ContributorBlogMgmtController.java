/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.service.BlogService;
import com.sg.bessieblog.service.UserService;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Nick
 */
public class ContributorBlogMgmtController {
//    BlogService blogService;
//    UserService userService;
//
//    @Inject
//    public ContributorBlogMgmtController(BlogService blogService, UserService userService) {
//        this.blogService = blogService;
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = {"/active_for_user"}, method = RequestMethod.GET)
//    @ResponseBody
//    public List<BlogViewModel> getListOfActivePages() {
//        User u = new User();
//        List<Blog> activePostsForUser = blogService.getBlogsByUserId(1);
//        return staticPageService.getListOfViewModelsFromListOfStaticPages(activePagesForUser);
//    }

//    @RequestMapping(value = {"/pending_for_user"}, method = RequestMethod.GET)
//    @ResponseBody
//    public List<StaticPageViewModel> getListOfPendingPages() {
//        User u = new User();
//        List<StaticPage> pendingPagesForUser = staticPageService.getAllPendingStaticPagesByUserId(1);
//        return staticPageService.getListOfViewModelsFromListOfStaticPages(pendingPagesForUser);
//    }
//
//    @RequestMapping(value = {"/expired_for_user"}, method = RequestMethod.GET)
//    @ResponseBody
//    public List<StaticPageViewModel> getListOfExpiredPages() {
//        User u = new User();
//        List<StaticPage> expiredPagesForUser = staticPageService.getAllExpiredStaticPagesByUserId(1);
//        return staticPageService.getListOfViewModelsFromListOfStaticPages(expiredPagesForUser);
//    }
}
