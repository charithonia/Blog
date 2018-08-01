/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.commandmodel.UserFormCommandModel;
import com.sg.bessieblog.service.UserService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/list_users"})
public class UserController {
    
    private UserService userService;
    
    @Inject
    public UserController(UserService userService) {
        
        this.userService = userService;
    }
    
    @RequestMapping(value = "/displayUserManagementPage", method = RequestMethod.GET)
    public String displayUserManagementPage(Model model) {

        return "list_users";
    }
    
    @RequestMapping(value = "/displayEditUser", method = RequestMethod.GET)
    public String displayEditUser(Model model, @RequestParam int userToEdit) {

        model.addAttribute("userToEdit", userToEdit);
        return "edit_user";

    }
    
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(Model model, UserFormCommandModel ufcm) {

        return "list_users";
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(Model model, UserFormCommandModel ufcm) {

        return "list_users";
    }
    
    public String removeUser(HttpServletRequest request, Model model) {

        return "list_users";
    }
    
}
