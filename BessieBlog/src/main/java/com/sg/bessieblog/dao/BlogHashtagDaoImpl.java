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
import com.sg.bessieblog.dto.BlogHashtag;
import com.sg.bessieblog.dto.Hashtag;


/**
 *
 * @author main
 */
public class BlogHashtagDaoImpl implements BlogHashtagDao {
    
    // sql
    private final String INSERT_BLOG_HASHTAG
	    = "insert into Blog_Hashtag "
	    + "(BlogId, HashtagId) "
	    + "values(?, ?)";
    
    private final String GET_BLOG_HASHTAG_BY_ID
	    = "select * from Blog_Hashtag where Id = ?";
    
    private final String GET_ALL_BLOG_HASHTAGS
	    = "select * from Blog_Hashtag";
    
    private final String GET_BLOG_HASHTAGS_BY_BLOG_ID
	    = "select * from Blog_Hashtag where BlogId = ?";
    
    private final String REMOVE_BLOG_HASHTAG
	    = "delete from Blog_Hashtag where Id = ?";
    
    private final String REMOVE_BLOG_FROM_HASHTAG
	    = "";
    
    private final String REMOVE_HASHTAG_FROM_BLOG
	    = "";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogHashtag insertBlogHashtag(BlogHashtag bh) {
	jdbcTemplate.update(INSERT_BLOG_HASHTAG,
		bh.getBlog().getId(),
		bh.getHashtag().getId());
	
	int blogHashtagId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
		Integer.class);
	bh.setId(blogHashtagId);
	
	return bh;
    }
    
    @Override
    public BlogHashtag getBlogHashtagById(int id) {
	try {
	    return jdbcTemplate.queryForObject(GET_BLOG_HASHTAG_BY_ID,
		    new BlogHashtagMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<BlogHashtag> getAllBlogHashtags() {
	return jdbcTemplate.query(GET_ALL_BLOG_HASHTAGS,
		new BlogHashtagMapper());
    }
    
    @Override
    public List<BlogHashtag> getBlogHashtagsByBlog(Blog blog) {
	return jdbcTemplate.query(GET_BLOG_HASHTAGS_BY_BLOG_ID,
		new BlogHashtagMapper(),
		blog.getId());
    }
    
    @Override
    public void removeBlogHashtag(BlogHashtag bh) {
	jdbcTemplate.update(REMOVE_BLOG_HASHTAG,
		bh.getId());
    }
    
    // What do these methods do? If they have to do with amending blogs or
    // hashtags, should they be in those DAOs or services?
    @Override
    public void removeBlogFromHashtag(Hashtag hashtag) {
	throw new UnsupportedOperationException();
    }
    
    @Override
    public void removeHashtagFromBlog(Blog blog) {
	throw new UnsupportedOperationException();
    }
    
    private static final class BlogHashtagMapper
	    implements RowMapper<BlogHashtag> {

	@Override
	public BlogHashtag mapRow(ResultSet rs, int i)
		throws SQLException {
	    BlogHashtag bh = new BlogHashtag();
	    
	    bh.setId(rs.getInt("Id"));
	    
	    Blog blog = new Blog();
	    blog.setId(rs.getInt("BlogId"));
	    bh.setBlog(blog);
	    
	    Hashtag hashtag = new Hashtag();
	    hashtag.setId(rs.getInt("HashtagId"));
	    bh.setHashtag(hashtag);
	    
	    return bh;
	}
    }
}
