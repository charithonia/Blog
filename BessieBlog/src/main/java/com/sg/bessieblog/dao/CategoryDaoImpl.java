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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sg.bessieblog.dto.Category;

/**
 *
 * @author main
 */
public class CategoryDaoImpl implements CategoryDao {
    
    // sql
    private final String INSERT_CATEGORY
	    = "insert into Category (Name) values(?)";
    
    private final String GET_CATEGORY_BY_ID
	    = "select * from Category where id = ?";
    
    private final String GET_ALL_CATEGORIES
	    = "select * from Category";
    
    private final String REMOVE_CATEGORY
	    = "delete from Category where id = ?";
    
    private final String UPDATE_CATEGORY
	    = "update Category set Name = ? "
	    + "where id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category insertCategory(Category category) {
	jdbcTemplate.update(INSERT_CATEGORY,
		category.getName());
	
	int categoryId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
		Integer.class);
	category.setId(categoryId);
	
	return category;
    }
    
    @Override
    public Category getCategoryById(int id) {
	try {
	    return jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID,
		new CategoryMapper(),
		id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<Category> getAllCategories() {
	return jdbcTemplate.query(GET_ALL_CATEGORIES,
		new CategoryMapper());
    }
    
    @Override
    public void removeCategory(Category category) {
	jdbcTemplate.update(REMOVE_CATEGORY,
		category.getId());
    }
    
    @Override
    public void updateCategory(Category category) {
	jdbcTemplate.update(UPDATE_CATEGORY,
		category.getName(),
		category.getId());
    }
    
    private static final class CategoryMapper
	    implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int i)
		throws SQLException {
	    Category category = new Category();
	    
	    category.setId(rs.getInt("id"));
	    category.setName(rs.getString("name"));
	    
	    return category;
	}
    }
}
