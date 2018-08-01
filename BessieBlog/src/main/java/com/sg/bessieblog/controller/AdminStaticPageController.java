/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.commandmodel.StaticPageFormCommandModel;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.service.StaticPageService;
import com.sg.bessieblog.service.UserService;
import com.sg.bessieblog.viewmodel.AdminStaticPageMgmtViewModel;
import com.sg.bessieblog.viewmodel.StaticPageViewModel;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping({"/admin_static_pages"})
public class AdminStaticPageController {
    
    StaticPageService staticPageService;
    UserService userService;
    
    @Inject
    public AdminStaticPageController(StaticPageService staticPageService, UserService userService) {
        this.staticPageService = staticPageService;
        this.userService = userService;
    }
    
    
    //listing-populating end points
    @RequestMapping(value = {"/all_active"}, method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPageViewModel> getListOfAllActivePages(){
        List<StaticPage> allActivePages = staticPageService.getAllActiveStaticPages();
        List<StaticPageViewModel> spvmList = staticPageService.getListOfViewModelsFromListOfStaticPages(allActivePages);
        return spvmList;
    }
    
    @RequestMapping(value = {"/all_pending"}, method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPageViewModel> getListOfAllPendingPages(){
        List<StaticPage> allPendingPages = staticPageService.getAllPendingStaticPages();
        return staticPageService.getListOfViewModelsFromListOfStaticPages(allPendingPages);
    }
    
    @RequestMapping(value = {"/all_expired"}, method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPageViewModel> getListOfAllExpiredPages(){
        List<StaticPage> allExpiredPages = staticPageService.getAllExpiredStaticPages();
        return staticPageService.getListOfViewModelsFromListOfStaticPages(allExpiredPages);
    }
}


/*
    //for reader
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayStaticPagesHome(Model model) {
        
        //should
        
        
        StaticPageViewModel spvm = staticPageService.getStaticPageViewModel(dfdf);
        model.addAttribute("spvm", spvm);
        return "roles";
    }
    
    //for admin/contributer 
    @RequestMapping(value = "/displayStaticPageToAdmin/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String displayStaticPageToAdmin(Model model, @PathVariable("id") int id) {
        StaticPage sp = staticPageService.getStaticPageById(id);
        User user = userService.eagerlyGetUserById(sp.getUser().getId()); //hoping this provides all the info on user down to the role name
        sp.setUser(user);
        
        model.addAttribute("spvm", staticPageService.getStaticPageViewModel(sp));
        
        return "view_static_page_details";
    }
    
    //admin
    @RequestMapping(value = "/displayEditStaticPage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public StaticPageViewModel displayEditStaticPage(Model model, 
            @RequestParam int staticPageToEdit) {
        
        StaticPage sp = staticPageService.getStaticPageById(staticPageToEdit);
        StaticPageViewModel uspvm = staticPageService.getStaticPageViewModel(sp);
        
        return uspvm; //this is pulled onto jsp from the ajax -- function(uspvm)

    }
    
    @RequestMapping(value = "/updateStaticPage/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStaticPage(@PathVariable("id") int id, 
            @Valid @RequestBody StaticPageFormCommandModel spfcm) 
            throws UpdateIntegrityException {

        //where exactly in the ajax is the spfcm funneled into this end point?
        //don't remember how validation happens here....

        
        if (id != spfcm.getStaticPageId()){
            throw new UpdateIntegrityException("Static page Id on URL must match Id in submitted data");
        }
        
        StaticPage sp = staticPageService.makeStaticPageFromCommandModel(spfcm);
        
        staticPageService.updateStaticPage(sp);
        
    }
    
    //
    @RequestMapping(value = "/displayCreateStaticPage", method = RequestMethod.GET)
    public String displayCreateStaticPage(Model model) {

        return "create_static_page";
        
    }
    
    @RequestMapping(value = "/createStaticPage", method = RequestMethod.POST)
    public String createStaticPage(Model model, StaticPageFormCommandModel spfcm) {

        return "static_page";
    }
    
    
    
    public String removeStaticPage(HttpServletRequest request, Model model) {

        return "static_page";
    }

*/