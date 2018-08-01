/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.BlogFormCommandModel;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

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
public class BlogServiceTest {
    
    @Inject
    private BlogService service;
    
    public BlogServiceTest() {
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
     * Test of insertBlog method, of class BlogService.
     */
    @Test
    @Transactional
    public void testInsertGetDeleteBlog() {       
        BlogFormCommandModel blog = new BlogFormCommandModel();
        
        blog.setBody("Body");
        blog.setCategoryId(1);
        blog.setCreationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        blog.setExperiationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        blog.setIsApproved(true);
        blog.setTitle("Title");
        blog.setUserId(1);
        blog.setPublicationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        
        Blog newBlog = service.insertBlog(blog);
        Blog fromDao = service.getBlogById(newBlog.getId());
        
        assertEquals(fromDao.getBody(), "Body");
        assertTrue(1 == fromDao.getCatagory().getId());
        assertEquals(fromDao.getCreationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertEquals(fromDao.getExpirationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertTrue(fromDao.getIsApproved());
        assertEquals(fromDao.getUser().getId(), 1);
        assertEquals(fromDao.getTitle(), "Title");
        assertEquals(fromDao.getPublicationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        
        service.removeBlog(newBlog);
        
        assertNull(service.getBlogById(newBlog.getId()));
        
    }
    
    @Test
    @Transactional
    public void testGetBlogById() {
	Blog blog = service.getBlogById(1);
	
	assertNotNull(blog);
	
	BlogFormCommandModel bfcm = service.getBlogFormCommandModel(blog);
	
	Blog blog2 = service.insertBlog(bfcm);
	
	int blog2_id = blog2.getId();
	Blog result = service.getBlogById(blog2_id);
	
	assertNotNull(result);
    }

    /**
     * Test of getAllBlogs method, of class BlogService.
     */
    @Test
    public void testGetAllBlogs() {
    }

    /**
     * Test of updateBlog method, of class BlogService.
     */
    @Test
    @Transactional
    public void testUpdateBlog() {
        BlogFormCommandModel bfcm = new BlogFormCommandModel();
        Category category = new Category();
        category.setId(1);
        User user = new User();
        user.setId(1);
        
        bfcm.setBody("Body");
        bfcm.setCategoryId(1);
        bfcm.setCreationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        bfcm.setExperiationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        bfcm.setIsApproved(true);
        bfcm.setTitle("Title");
        bfcm.setUserId(1);
        bfcm.setPublicationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        
        Blog newBlog = service.insertBlog(bfcm);
        Blog fromDao = service.getBlogById(newBlog.getId());
        BlogFormCommandModel bfcmFromDao = service.getBlogFormCommandModel(fromDao);
        assertEquals(fromDao.getBody(), "Body");
        assertTrue(1 == fromDao.getCatagory().getId());
        assertEquals(fromDao.getCreationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertEquals(fromDao.getExpirationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertTrue(fromDao.getIsApproved());
        assertEquals(fromDao.getUser().getId(), 1);
        assertEquals(fromDao.getTitle(), "Title");
        assertEquals(fromDao.getPublicationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        
        bfcmFromDao.setBody("updatedBody");
        bfcmFromDao.setTitle("updatedTitle");  
        
        service.updateBlog(bfcmFromDao);
        
        Blog updatedBlog = service.getBlogById(fromDao.getId());
        
        assertEquals(updatedBlog.getBody(), "updatedBody");
        assertTrue(1 == updatedBlog.getCatagory().getId());
        assertEquals(updatedBlog.getCreationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertEquals(updatedBlog.getExpirationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        assertTrue(updatedBlog.getIsApproved());
        assertEquals(updatedBlog.getUser().getId(), 1);
        assertEquals(updatedBlog.getTitle(), "updatedTitle");
        assertEquals(updatedBlog.getPublicationDate(), LocalDateTime.parse("2013-12-13T13:45:19"));
        
        service.removeBlog(updatedBlog);
    }

    /**
     * Test of getBlogsByStatus method, of class BlogService.
     */
    @Test
    public void testGetBlogsByStatus() {
    }

    /**
     * Test of getBlogsByDate method, of class BlogService.
     */
    @Test
    public void testGetBlogsByDate() {
    }

    /**
     * Test of getBlogsByCategory method, of class BlogService.
     */
    @Test
    public void testGetBlogsByCategory() {
    }

    /**
     * Test of getBlogsByUserId method, of class BlogService.
     */
    @Test
    public void testGetBlogsByUserId() {
    }

    /**
     * Test of getBlogsByHashtag method, of class BlogService.
     */
    @Test
    public void testGetBlogsByHashtag() {
    }

    /**
     * Test of getAllActiveBlogs method, of class BlogService.
     */
    @Test
    public void testGetAllActiveBlogs() {
    }

    /**
     * Test of getAllPendingBlogs method, of class BlogService.
     */
    @Test
    public void testGetAllPendingBlogs() {
    }

    /**
     * Test of getBlogFormCommandModel method, of class BlogService.
     */
    @Test
    public void testGetBlogFormCommandModelNullFields() {
//        Blog blog = new Blog();
//        BlogFormCommandModel bfcm = service.getBlogFormCommandModel(blog);
//        
//        assertTrue(bfcm.getCategoryList().size() == 5);
    }

    /**
     * Test of getHomeViewModel method, of class BlogService.
     */
    @Test
    public void testGetHomeViewModel() {
        HomeViewModel hvm = service.getHomeViewModel();
        assertEquals(hvm.getCategoryList().size(), 5);
        assertEquals(hvm.getStaticPages().size(), 2);
        assertEquals(hvm.getBlogs().size(), 1);
    }

    /**
     * Test of getBlogViewModel method, of class BlogService.
     */
    @Test
    public void testGetBlogViewModel() {
        Blog blog = service.getBlogById(1);
        BlogViewModel bvm = service.getBlogViewModel(blog);
        assertEquals(bvm.getBlogId(), 1);
        assertEquals(bvm.getExpirationDate(), LocalDateTime.parse("2018-10-22T10:10:10"));
        assertEquals(bvm.getPublicationDate(), LocalDateTime.parse("2015-10-22T10:10:10"));
        assertEquals(bvm.getUser().getId(), 1);
        assertEquals(bvm.getCategory().getId(), 1);
        assertEquals(bvm.getBody(), "ajfkjaf;;asl;ffa;kjd;l");
        assertTrue(bvm.getIsApproved());
        assertEquals(bvm.getCategoryList().size(), 5);
        assertEquals(bvm.getSpList().size(), 2);        
    }



    /**
     * Test of eagerlyGetBlogById method, of class BlogService.
     */
    @Test
    public void testEagerlyGetBlogById() {
        Blog blog = service.eagerlyGetBlogById(1);
        assertEquals(blog.getCatagory().getName(), "Food");
        assertEquals(blog.getUser().getName(), "Bessie");
        assertEquals(blog.getUser().getEmail(), "bessie@gmail.com");
        assertEquals(blog.getUser().getRole().getId(), 1);        
    }

    /**
     * Test of removeHashtagFromBlog method, of class BlogService.
     */
    @Test
    public void testRemoveHashtagFromBlog() {
    }
}
