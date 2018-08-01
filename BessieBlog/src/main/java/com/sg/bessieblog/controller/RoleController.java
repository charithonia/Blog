/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.commandmodel.RoleFormCommandModel;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.service.RoleService;
import com.sg.bessieblog.viewmodel.RolePageViewModel;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping({"/roles"})
public class RoleController {

    RoleService roleService;

    @Inject
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayRoleHome(Model model) {
        RolePageViewModel rpvm = roleService.getRolePageViewModel();
        model.addAttribute("rpvm", rpvm);
        return "roles";
    }

    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    public String createRole(Model model, RoleFormCommandModel rfcm) {
        return "roles";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public Role getRole(@PathVariable("id") int id) {
        return roleService.getRoleById(id);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRole(@PathVariable("id") int id, @RequestBody RoleFormCommandModel rfcm) {
        rfcm.setRoleId(id);
        roleService.updateRole(rfcm);
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Role createRole(@RequestBody Role role) {
        return roleService.insertRole(role);
    }

    @RequestMapping(value = {"/roleList"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getAllRoles() {
        List<Role> list = roleService.getAllRoles();
        return list;

    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") int id) {        
        roleService.removeRole(id);
    }

}
