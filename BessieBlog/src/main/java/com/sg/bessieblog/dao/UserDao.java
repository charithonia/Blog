/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.util.List;

import com.sg.bessieblog.dto.User;

/**
 *
 * @author main
 */
public interface UserDao {
    
    public User insertUser(User user);
    
    public User getUserById(int id);
    
    public List<User> getAllUsers();
    
    public List<User> getAllAdmins();
    
    public List<User> getAllContributors();
    
    public void removeUser(User user);
    
    public void updateUser(User user);
}
