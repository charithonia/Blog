/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.User;

/**
 *
 * @author main
 */
public class BlogDaoImpl implements BlogDao {
    
    // sql
    private final String INSERT_BLOG
	    = "INSERT INTO Blog (Title, CreationDate, ExpirationDate, PublicationDate, UserId, CategoryId, Body, isApproved) VALUES(?,?,?,?,?,?,?,?)";
    
    private final String GET_BLOG_BY_ID
	    = "SELECT * FROM Blog WHERE id = ?";
    
    private final String GET_ALL_BLOGS
	    = "select * from Blog";
    
    private final String REMOVE_BLOG
	    = "DELETE FROM Blog WHERE id = ?";
    
    private final String UPDATE_BLOG
	    = "UPDATE Blog SET Title=?, CreationDate=?, ExpirationDate=?, PublicationDate=?, UserId=?, CategoryId=?, Body=?, isApproved=? WHERE id = ?";
    
    private final String GET_BLOGS_BY_STATUS
	    = "select * from Blog where status = ?";
    
    private final String GET_BLOGS_BY_DATE
	    = "select * from Blog where PublicationDate = ?";
    
    private final String GET_ACTIVE_BLOGS_BY_CATEGORY
	    = "select * from Blog where CategoryId = ? and (IsApproved=1 and PublicationDate <= now() and (ExpirationDate is null or ExpirationDate > now()))";
    
    private final String GET_BLOGS_BY_USER_ID
	    = "select * from Blog where UserId = ?";
    
    private final String GET_BLOGS_BY_HASHTAG
	    = "select Blog.* from Blog "
	    + "inner join Blog_Hashtag "
	    + "on Blog.Id = Blog_Hashtag.BlogId "
	    + "where Blog_Hashtag.HashtagId = ? "
	    + "order by Blog.PublicationDate";
    
    private final String GET_ALL_ACTIVE_BLOGS
	    = "select * from Blog where (IsApproved=1 and PublicationDate <= now() and (ExpirationDate is null or ExpirationDate > now()))";
    
    private final String GET_ALL_PENDING_BLOGS
            = "SELECT * FROM Blog "
            + "WHERE (Id) not in "
            + "(SELECT Id FROM Blog where (IsApproved=1 and PublicationDate <= now() and (ExpirationDate is null or ExpirationDate > now()) or ExpirationDate <= now()) "
            + ");";

    private final String GET_ALL_EXPIRED_BLOGS
            = "SELECT * FROM Blog where ExpirationDate <= now()";

    private final String GET_ALL_ACTIVE_BLOGS_BY_USER_ID
            = "SELECT * FROM Blog where (IsApproved=1 and PublicationDate <= now() and (ExpirationDate is null or ExpirationDate > now()) and UserId=?)";

    private final String GET_ALL_PENDING_BLOGS_BY_USER_ID
            = "SELECT * FROM Blog "
            + "WHERE UserId = ? and (Id) not in "
            + "(SELECT Id FROM Blog where "
            + "(IsApproved=1 and PublicationDate <= now() "
            + "and (ExpirationDate is null or ExpirationDate > now()) or ExpirationDate <= now())"
            + ");";

    private final String GET_ALL_EXPIRED_BLOGS_BY_USER_ID
            = "SELECT * FROM Blog where ExpirationDate <= now() and UserId=?;";
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Blog insertBlog(Blog blog) {
	Timestamp timestampCreationDate;
	if (blog.getCreationDate() != null) {
	    timestampCreationDate = Timestamp.valueOf(blog.getCreationDate());
	}
	else {
	    timestampCreationDate = null;
	}
	
	Timestamp timestampPublicationDate;
	if (blog.getPublicationDate()!= null) {
	    timestampPublicationDate = Timestamp.valueOf(blog.getPublicationDate());
	}
	else {
	    timestampPublicationDate = null;
	}
	
	Timestamp timestampExpirationDate;
	if (blog.getExpirationDate()!= null) {
	    timestampExpirationDate = Timestamp.valueOf(blog.getExpirationDate());
	}
	else {
	    timestampExpirationDate = null;
	}
	
        jdbcTemplate.update(INSERT_BLOG, 
                blog.getTitle(),
                timestampCreationDate,
                timestampExpirationDate,
                timestampPublicationDate,
                blog.getUser().getId(),
                blog.getCatagory().getId(),
                blog.getBody(),
                blog.getIsApproved());
        int blogId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        blog.setId(blogId);
        return blog;
    }
    
    @Override
    public Blog getBlogById(int id) {
	 try {
            return jdbcTemplate.queryForObject(GET_BLOG_BY_ID,
                    new BlogMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Blog> getAllBlogs() {
	return jdbcTemplate.query(GET_ALL_BLOGS,
		new BlogMapper());
    }
    
    @Override
    public void removeBlog(Blog blog) {
	 jdbcTemplate.update(REMOVE_BLOG, blog.getId());
    }
    
    @Override
    public void updateBlog(Blog blog) {
	 jdbcTemplate.update(UPDATE_BLOG,
                blog.getTitle(),
                blog.getCreationDate().toString(),
                blog.getExpirationDate().toString(),
                blog.getPublicationDate().toString(),
                blog.getUser().getId(),
                blog.getCatagory().getId(),
                blog.getBody(),
                blog.getIsApproved(),
                blog.getId());
    }
    
    @Override
    public List<Blog> getBlogsByStatus(String status) {
	return jdbcTemplate.query(GET_BLOGS_BY_STATUS,
		new BlogMapper(),
		status);
    }
    
    @Override
    public List<Blog> getBlogsByDate(LocalDateTime date) {
	return jdbcTemplate.query(GET_BLOGS_BY_DATE,
		new BlogMapper(),
		date);
    }
    
    @Override
    public List<Blog> getBlogsByCategory(Category category) {
	return jdbcTemplate.query(GET_ACTIVE_BLOGS_BY_CATEGORY,
		new BlogMapper(),
		category.getId());
    }
    
    @Override
    public List<Blog> getBlogsByUserId(User user) {
	return jdbcTemplate.query(GET_BLOGS_BY_USER_ID,
		new BlogMapper(),
		user.getId());
    }
    
    @Override
    public List<Blog> getBlogsByHashtag(Hashtag hashtag) {
	return jdbcTemplate.query(GET_BLOGS_BY_HASHTAG,
		new BlogMapper(),
		hashtag.getId());
    }
    
    // Covered by getBlogsByStatus()?
    @Override
    public List<Blog> getAllActiveBlogs() {
	return jdbcTemplate.query(GET_ALL_ACTIVE_BLOGS,
		new BlogMapper());
    }
    
    // Covered by getBlogsByStatus()?
    @Override
    public List<Blog> getAllPendingBlogs() {
	return jdbcTemplate.query(GET_ALL_PENDING_BLOGS,
		new BlogMapper());
    }
    // Covered by getBlogsByStatus()?
    @Override
    public List<Blog> getAllExpiredBlogs() {
	return jdbcTemplate.query(GET_ALL_EXPIRED_BLOGS,
		new BlogMapper());
    }
    @Override
    public List<Blog> getAllActiveBlogsByUserId(User user) {
	return jdbcTemplate.query(GET_ALL_ACTIVE_BLOGS_BY_USER_ID,
		new BlogMapper(), user.getId());
    }
    
    // Covered by getBlogsByStatus()?
    @Override
    public List<Blog> getAllPendingBlogsByUserId(User user) {
	return jdbcTemplate.query(GET_ALL_PENDING_BLOGS_BY_USER_ID,
		new BlogMapper(), user.getId());
    }
    // Covered by getBlogsByStatus()?
    @Override
    public List<Blog> getAllExpiredBlogsByUserId(User user) {
	return jdbcTemplate.query(GET_ALL_EXPIRED_BLOGS_BY_USER_ID,
		new BlogMapper(),user.getId());
    }
    
    private static final class BlogMapper
	    implements RowMapper<Blog> {

	@Override
	public Blog mapRow(ResultSet rs, int i)
		throws SQLException {
	    Blog blog = new Blog();
	    java.sql.Timestamp timestamp;
	    
	    Category cat = new Category();
            cat.setId(rs.getInt("categoryId"));
            User user = new User();
            user.setId(rs.getInt("userId"));
            
            blog.setBody(rs.getString("body"));
            blog.setCategory(cat);
	    
	    timestamp = rs.getTimestamp("CreationDate");
	    if (timestamp != null) {
		blog.setCreationDate(timestamp.toLocalDateTime());
	    }
	    else {
		blog.setCreationDate(null);
	    }
	    
	    timestamp = rs.getTimestamp("PublicationDate");
	    if (timestamp != null) {
		blog.setPublicationDate(timestamp.toLocalDateTime());
	    }
	    else {
		blog.setPublicationDate(null);
	    }
	    
	    timestamp = rs.getTimestamp("ExpirationDate");
	    if (timestamp != null) {
		blog.setExpirationDate(timestamp.toLocalDateTime());
	    }
	    else {
		blog.setExpirationDate(null);
	    }
            
            blog.setId(rs.getInt("id"));
            blog.setIsApproved(rs.getBoolean("isApproved"));
            blog.setTitle(rs.getString("Title"));
            blog.setUser(user);
	    
	    return blog;
	}
    }
}