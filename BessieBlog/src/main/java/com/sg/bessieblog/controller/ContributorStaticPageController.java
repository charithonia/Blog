/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.service.StaticPageService;
import com.sg.bessieblog.service.UserService;
import com.sg.bessieblog.viewmodel.StaticPageViewModel;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author matt
 */
@Controller
@RequestMapping({"/cont_static_pages"})
public class ContributorStaticPageController{

    StaticPageService staticPageService;
    UserService userService;

    @Inject
    public ContributorStaticPageController(StaticPageService staticPageService, UserService userService) {
        this.staticPageService = staticPageService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/active_for_user"}, method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPageViewModel> getListOfActivePages() {
        User u = new User();
        List<StaticPage> activePagesForUser = staticPageService.getAllActiveStaticPagesByUserId(1);
        return staticPageService.getListOfViewModelsFromListOfStaticPages(activePagesForUser);
    }

    @RequestMapping(value = {"/pending_for_user"}, method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPageViewModel> getListOfPendingPages() {
        User u = new User();
        List<StaticPage> pendingPagesForUser = staticPageService.getAllPendingStaticPagesByUserId(1);
        return staticPageService.getListOfViewModelsFromListOfStaticPages(pendingPagesForUser);
    }

    @RequestMapping(value = {"/expired_for_user"}, method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPageViewModel> getListOfExpiredPages() {
        User u = new User();
        List<StaticPage> expiredPagesForUser = staticPageService.getAllExpiredStaticPagesByUserId(1);
        return staticPageService.getListOfViewModelsFromListOfStaticPages(expiredPagesForUser);
    }

}
