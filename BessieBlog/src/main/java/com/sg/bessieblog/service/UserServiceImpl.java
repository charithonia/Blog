/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.UserFormCommandModel;
import com.sg.bessieblog.dao.UserDao;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.UserMgmtViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
public class UserServiceImpl implements UserService {
    
    private UserDao userDao;
    private StaticPageService spService;
    private RoleService roleService;
    

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    
    public void setStaticPageService(StaticPageService spService){
        this.spService = spService;
    }
    
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public User insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getAllAdmins() {
        return userDao.getAllAdmins();
    }

    @Override
    public List<User> getAllContributors() {
        return userDao.getAllContributors();
    }

    //need to check for and ultimately add a throws exception if user in use
    //instead of just allowing deletion of user since that would require
    //deleting all associated blogs and static pages
    @Override
    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public UserMgmtViewModel getUserMgmtViewModel() {
        
        UserMgmtViewModel umvm = new UserMgmtViewModel();
        
        List<User> userList = userDao.getAllUsers();
        umvm.setUserList(userList);
        
        List<StaticPage> staticPageList = spService.getAllStaticPages();
        umvm.setStaticPageList(staticPageList);
        
        return umvm;
    }

    @Override
    public UserFormCommandModel getUserFormCommandModel(User user) {
        
        UserFormCommandModel ufcm = new UserFormCommandModel();
        
        ufcm.setId(user.getId());
        ufcm.setName(user.getName());
        ufcm.setRoleId(user.getRole().getId());
        ufcm.setEmail(user.getEmail());
        
        List<Role> roles = roleService.getAllRoles();
        ufcm.setRoles(roles);
        
        return ufcm;
    }

    @Override
    public User eagerlyGetUserById(int userId) {
        
        User user = userDao.getUserById(userId);
        
        Role role = roleService.getRoleById(user.getRole().getId());
        user.getRole();
        
        return user;
    }
    
}
