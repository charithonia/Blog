/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.User;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class BlogDaoTest {
    
    @Inject
    private ApplicationContext ctx;
    
    @Inject
    private BlogDao dao;
    
    public BlogDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of getBlogById method, of class BlogDao.
     */
    @Test
    @Transactional
    public void testAddGetDeleteBlog() {
        Blog blog = new Blog();
        Category category = new Category();
        category.setId(1);
        User user = new User();
        user.setId(1);
        
        blog.setBody("Body");
        blog.setCategory(category);
        blog.setCreationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        blog.setExpirationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        blog.setIsApproved(true);
        blog.setTitle("Title");
        blog.setUser(user);
        blog.setPublicationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        
        Blog newBlog = dao.insertBlog(blog);
        Blog fromDao = dao.getBlogById(newBlog.getId());
        
        assertEquals(fromDao.getBody(), "Body");
        assertTrue(1 == fromDao.getCatagory().getId());
        assertEquals(fromDao.getCreationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertEquals(fromDao.getExpirationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertTrue(fromDao.getIsApproved());
        assertEquals(fromDao.getUser().getId(), 1);
        assertEquals(fromDao.getTitle(), "Title");
        assertEquals(fromDao.getPublicationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        
        dao.removeBlog(newBlog);
        
        assertNull(dao.getBlogById(newBlog.getId()));
    }

    /**
     * Test of getAllBlogs method, of class BlogDao.
     */
    @Test
    public void testGetAllBlogs() {
    }

    /**
     * Test of updateBlog method, of class BlogDao.
     */
    @Test
    @Transactional
    public void testUpdateBlog() {
         Blog blog = new Blog();
        Category category = new Category();
        category.setId(1);
        User user = new User();
        user.setId(1);
        
        blog.setBody("Body");
        blog.setCategory(category);
        blog.setCreationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        blog.setExpirationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        blog.setIsApproved(true);
        blog.setTitle("Title");
        blog.setUser(user);
        blog.setPublicationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        
        Blog newBlog = dao.insertBlog(blog);
        Blog fromDao = dao.getBlogById(newBlog.getId());
        
        assertEquals(fromDao.getBody(), "Body");
        assertTrue(1 == fromDao.getCatagory().getId());
        assertEquals(fromDao.getCreationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertEquals(fromDao.getExpirationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertTrue(fromDao.getIsApproved());
        assertEquals(fromDao.getUser().getId(), 1);
        assertEquals(fromDao.getTitle(), "Title");
        assertEquals(fromDao.getPublicationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        
        fromDao.setBody("updatedBody");
        fromDao.setTitle("updatedTitle");
        dao.updateBlog(fromDao);
        
        Blog updatedBlog = dao.getBlogById(fromDao.getId());
        
        assertEquals(fromDao.getBody(), "updatedBody");
        assertTrue(1 == fromDao.getCatagory().getId());
        assertEquals(fromDao.getCreationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertEquals(fromDao.getExpirationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertTrue(fromDao.getIsApproved());
        assertEquals(fromDao.getUser().getId(), 1);
        assertEquals(fromDao.getTitle(), "updatedTitle");
        assertEquals(fromDao.getPublicationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        
        dao.removeBlog(fromDao);
    }

    /**
     * Test of getBlogsByStatus method, of class BlogDao.
     */
    @Test
    public void testGetBlogsByStatus() {
    }

    /**
     * Test of getBlogsByDate method, of class BlogDao.
     */
    @Test
    public void testGetBlogsByDate() {
    }

    /**
     * Test of getBlogsByCategory method, of class BlogDao.
     */
    @Test
    public void testGetBlosgByCategory() {
    }

    /**
     * Test of getBlogsByUserId method, of class BlogDao.
     */
    @Test
    public void testGetBlogsByUserId() {
    }

    /**
     * Test of getBlogsByHashtag method, of class BlogDao.
     */
    @Test
    public void testGetBlogsByHashtag() {
    }

    /**
     * Test of getAllActiveBlogs method, of class BlogDao.
     */
    @Test
    public void testGetAllActiveBlogs() {
        assertEquals(dao.getAllActiveBlogs().size(),1);
    }

    /**
     * Test of getAllPendingBlogs method, of class BlogDao.
     */
    @Test
    public void testGetAllPendingBlogs() {
        List<Blog> blogs = dao.getAllPendingBlogs();
        assertEquals(blogs.size(),3);
    }

  
    @Test
    public void testGetBlogsByCategory() {
    }

    /**
     * Test of getAllExpiredBlogs method, of class BlogDao.
     */
    @Test
    public void testGetAllExpiredBlogs() {
        assertEquals(dao.getAllExpiredBlogs().size(), 1);
    }

    /**
     * Test of getAllActiveBlogsByUserId method, of class BlogDao.
     */
    @Test
    public void testGetAllActiveBlogsByUserId() {
        User user = new User();
        user.setId(1);
        assertEquals(dao.getAllActiveBlogsByUserId(user).size(), 1);
    }

    /**
     * Test of getAllPendingBlogsByUserId method, of class BlogDao.
     */
    @Test
    public void testGetAllPendingBlogsByUserId() {
        User user = new User();
        user.setId(5);
        assertEquals(dao.getAllPendingBlogsByUserId(user).size(), 1);
        
    }

    /**
     * Test of getAllExpiredBlogsByUserId method, of class BlogDao.
     */
    @Test
    public void testGetAllExpiredBlogsByUserId() {
        User user = new User();
        user.setId(5);
        assertEquals(dao.getAllExpiredBlogsByUserId(user).size(), 1);
    }

    
}
