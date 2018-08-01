/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sg.bessieblog.dto.Role;

/**
 *
 * @author main
 */
public class RoleDaoImpl implements RoleDao {

    // sql
    private final String INSERT_ROLE
            = "insert into Role (Name) values(?)";

    private final String GET_ROLE_BY_ID
            = "select * from Role where id = ?";

    private final String GET_ALL_ROLES
            = "select * from Role";

    private final String REMOVE_ROLE
            = "delete from Role where id = ?";

    private final String UPDATE_ROLE
            = "update Role set "
            + "Name = ? "
            + "where id = ?";

    private final String GET_ROLE_BY_USER_ID
            = "Select r.* from Role r\n"
            + "inner join `User` u on u.RoleId = r.Id\n"
            + "where u.Id = ?;";

    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Role insertRole(Role role) {
        jdbcTemplate.update(INSERT_ROLE,
                role.getName());

        int roleId = jdbcTemplate.queryForObject("select last_insert_id()",
                Integer.class);
        role.setId(roleId);

        return role;
    }

    @Override
    public Role getRoleById(int id) {
        try {
            return jdbcTemplate.queryForObject(GET_ROLE_BY_ID,
                    new RoleMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return jdbcTemplate.query(GET_ALL_ROLES,
                new RoleMapper());
    }

    @Override
    public void removeRole(Role role) {
        jdbcTemplate.update(REMOVE_ROLE,
                role.getId());
    }

    @Override
    public void updateRole(Role role) {
        jdbcTemplate.update(UPDATE_ROLE,
                role.getName(),
                role.getId());
    }

    @Override
    public Role getRoleByUserId(int id) {
        return jdbcTemplate.queryForObject(GET_ROLE_BY_USER_ID, new RoleMapper(), id);
    }

    private static final class RoleMapper
            implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i)
                throws SQLException {
            Role role = new Role();

            role.setId(rs.getInt("Id"));
            role.setName(rs.getString("Name"));

            return role;
        }
    }
}
