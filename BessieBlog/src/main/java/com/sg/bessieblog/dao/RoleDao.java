/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.bessieblog.dto.Role;

/**
 *
 * @author main
 */
public interface RoleDao {
    
    public Role insertRole(Role role);
    
    public Role getRoleById(int id);
    
    public List<Role> getAllRoles();
    
    public void removeRole(Role role);
    
    public void updateRole(Role role);

    public Role getRoleByUserId(int id);
}
