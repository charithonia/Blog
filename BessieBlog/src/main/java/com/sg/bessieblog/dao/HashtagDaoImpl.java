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

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Hashtag;

/**
 *
 * @author main
 */
public class HashtagDaoImpl implements HashtagDao {
    
    // sql
    private final String INSERT_HASHTAG
	    = "insert into Hashtag (Name) values(?)";
    
    private final String GET_HASHTAG_BY_ID
	    = "select * from Hashtag where id = ?";
    
    private final String GET_HASHTAG_BY_NAME
	    = "select * from Hashtag where Name = ?";
    
    private final String GET_ALL_HASHTAGS
	    = "select * from Hashtag "
	    + "order by char_length(name) desc";
    
    private final String GET_HASHTAGS_BY_BLOG_ID
	    = "select Hashtag.* from Hashtag "
	    + "inner join Blog_Hashtag "
	    + "on Hashtag.Id = Blog_Hashtag.HashtagId "
	    + "where Blog_Hashtag.BlogId = ? "
	    + "order by char_length(Hashtag.name) desc";
    
    private final String REMOVE_HASHTAG
	    = "delete from Hashtag where id = ?";
    
    private final String HASHTAG_EXISTS_BY_ID
	    = "select count(1) from Hashtag where Id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Hashtag insertHashtag(Hashtag hashtag) {
	jdbcTemplate.update(INSERT_HASHTAG,
		hashtag.getName());
	
	int hashtagId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
		Integer.class);
	hashtag.setId(hashtagId);
	
	return hashtag;
    }
    
    @Override
    public Hashtag getHashtagById(int id) {
	try {
	    return jdbcTemplate.queryForObject(GET_HASHTAG_BY_ID,
		    new HashtagMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public Hashtag getHashtagByName(String name) {
	try {
	    return jdbcTemplate.queryForObject(GET_HASHTAG_BY_NAME,
		    new HashtagMapper(),
		    name);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<Hashtag> getAllHashtags() {
	return jdbcTemplate.query(GET_ALL_HASHTAGS,
		new HashtagMapper());
    }
    
    @Override
    public List<Hashtag> getHashtagsByBlog(Blog blog) {
	return jdbcTemplate.query(GET_HASHTAGS_BY_BLOG_ID,
		new HashtagMapper(),
		blog.getId());
    }
    
    @Override
    public void removeHashtag(Hashtag hashtag) {
	jdbcTemplate.update(REMOVE_HASHTAG,
		hashtag.getId());
    }
    
    @Override
    public boolean hashtagExists(Hashtag hashtag) {
	boolean result = jdbcTemplate.queryForObject(HASHTAG_EXISTS_BY_ID,
		Boolean.class);
	
	return result;
    }
    
    private static final class HashtagMapper
	    implements RowMapper<Hashtag> {

	@Override
	public Hashtag mapRow(ResultSet rs, int i)
		throws SQLException {
	    Hashtag hashtag = new Hashtag();
	    
	    hashtag.setId(rs.getInt("Id"));
	    hashtag.setName(rs.getString("Name"));
	    
	    return hashtag;
	}
    }
}
