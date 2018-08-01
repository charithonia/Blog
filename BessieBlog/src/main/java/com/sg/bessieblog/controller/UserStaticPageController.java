/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.commandmodel.StaticPageFormCommandModel;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.service.StaticPageService;
import com.sg.bessieblog.service.UserService;
import com.sg.bessieblog.viewmodel.StaticPageViewModel;
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

/**
 *
 * @author matt
 */
@Controller
@RequestMapping({"/user_static_pages"})
public class UserStaticPageController {
    
    StaticPageService staticPageService;
    UserService userService;

    @Inject
    public UserStaticPageController(StaticPageService staticPageService, UserService userService) {
        this.staticPageService = staticPageService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayStaticPageHome(Model model) {
        model.addAttribute("spvm", staticPageService.getStaticPageViewModel());
        return "static_pages";
    }
    
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
     public StaticPage createStaticPage(@RequestBody StaticPageFormCommandModel spfcm) {
        return staticPageService.insertStaticPage(spfcm);
    }
    
    @RequestMapping(value = {"/get/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public StaticPageViewModel getStaticPage(@PathVariable("id") int id) {
        StaticPage sp = staticPageService.getStaticPageById(id);
        return staticPageService.getStaticPageViewModel(sp);
    }
    
    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStaticPage(@PathVariable("id") String id, @RequestBody StaticPageFormCommandModel spfcm) {
        spfcm.setId(id);
        staticPageService.updateStaticPage(spfcm);
    }
    
    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaticPage(@PathVariable("id") int id) {        
        staticPageService.removeStaticPage(id);
    }
    
}
