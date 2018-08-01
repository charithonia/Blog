/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.RoleFormCommandModel;
import com.sg.bessieblog.dao.RoleDao;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.viewmodel.RolePageViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
public class RoleServiceImpl implements RoleService{
   private RoleDao roleDao;
   private StaticPageService spService;

    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    public void setStaticPageService(StaticPageService spService){
        this.spService = spService;
    }

    @Override
    public Role insertRole(Role role) {
        return roleDao.insertRole(role);
    }

    @Override
    public Role getRoleById(int roleId) {
        return roleDao.getRoleById(roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    //need to check for and ultimately add a throws exception if role in use
    //instead of just allowing deletion of role since that would require
    //deleting all associated users
    @Override
    public void removeRole(Role role) {
        roleDao.removeRole(role);
    }
    @Override
    public void removeRole(int roleId) {
        Role role = new Role();
        role.setId(roleId);
        roleDao.removeRole(role);
    }

    @Override
    public void updateRole(RoleFormCommandModel rfcm) {
        Role r = new Role();
        r.setId(rfcm.getRoleId());
        r.setName(rfcm.getName());
        
        roleDao.updateRole(r);
    }

    @Override
    public RolePageViewModel getRolePageViewModel() {
        RolePageViewModel rpvm = new RolePageViewModel();
	
	List<Role> roles = roleDao.getAllRoles();
	rpvm.setRoleList(roles);
	
	List<StaticPage> pages = spService.getAllActiveStaticPages();
	rpvm.setStaticPageList(pages);
	
	return rpvm;
    }

    @Override
    public RoleFormCommandModel getRoleFormCommandModel(Role role) {
        RoleFormCommandModel rfcm = new RoleFormCommandModel();
       rfcm.setRoleId(role.getId());
       rfcm.setName(role.getName());
       List<StaticPage> spList = spService.getAllStaticPages();
       rfcm.setSpList(spList);
       return rfcm;
    }

    @Override
    public Role getRoleByUserId(int id) {
        return roleDao.getRoleByUserId(id);
    }

    
}
