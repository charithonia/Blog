/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sg.bessieblog.dto.User;

/**
 *
 * @author main
 */
public class UserDaoImpl implements UserDao {
    
    // sql
    private final String INSERT_USER
	    = "insert into User(Name, Email, RoleId) values(?, ?, ?)";
    
    private final String GET_USER_BY_ID
	    = "select * from User where Id = ?;";
    
    private final String GET_ALL_USERS
	    = "select *from User";
    
    private final String GET_ALL_ADMINS
	    = "select u.Id, u.Name, u.Email, u.RoleId from User u "
            + "inner join Role r on u.RoleId = r.Id "
            + "where r.Name = 'Admin'";
    
    private final String GET_ALL_CONTRIBUTORS
	    = "select u.Id, u.Name, u.Email, u.RoleId from User u "
            + "inner join Role r on u.RoleId = r.Id "
            + "where r.Name = 'Contributor'";
    
    private final String REMOVE_USER
	    = "delete from User where Id = ?";
    
    private final String UPDATE_USER
	    = "update User set "
            + "Name = ?, Email = ?, RoleId = ? "
            + "where Id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User insertUser(User user) {
	
        jdbcTemplate.update(INSERT_USER,
                user.getName(),
                user.getEmail(),
                user.getRole().getId());
	
	int userId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
	user.setId(userId);
	
	return user;
        
    }
    
    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, new UserMapper(), id);
    }
    
    @Override
    public List<User> getAllUsers() {
	return jdbcTemplate.query(GET_ALL_USERS, new UserMapper());
    }
    
    @Override
    public List<User> getAllAdmins() {
	return jdbcTemplate.query(GET_ALL_ADMINS, new UserMapper());
    }
    
    @Override
    public List<User> getAllContributors() {
	return jdbcTemplate.query(GET_ALL_CONTRIBUTORS, new UserMapper());
    }
    
    @Override
    public void removeUser(User user) {
	jdbcTemplate.update(REMOVE_USER, user.getId());
    }
    
    @Override
    public void updateUser(User user) {
	jdbcTemplate.update(UPDATE_USER,
                user.getName(),
                user.getEmail(),
                user.getRole().getId(),
                user.getId());
        
    }
    
    private static final class UserMapper
	    implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int i)
		throws SQLException {
	    User user = new User();
	    
	    user.setId(rs.getInt("Id"));
            user.setName(rs.getString("Name"));
            user.setEmail(rs.getString("Email"));
            
            //lazy load role
            Role role = new Role();
            role.setId(rs.getInt("RoleId"));
            
            user.setRole(role);
	    
	    return user;
	}
    }
}
