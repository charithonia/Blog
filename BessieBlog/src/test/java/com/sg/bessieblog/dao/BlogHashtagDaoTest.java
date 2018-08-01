/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

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

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.BlogHashtag;
import com.sg.bessieblog.dto.Hashtag;

/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class BlogHashtagDaoTest {
    
    @Inject
    private BlogDao blogDao;
    
    @Inject
    private BlogHashtagDao blgHtgDao;
    
    @Inject
    private HashtagDao hashtagDao;
    
    public BlogHashtagDaoTest() {
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
     * Test of insertBlogHashtag method, of class BlogHashtagDao.
     */
    @Test
    public void testInsertBlogHashtag() {
    }

    /**
     * Test of getBlogHashtagById method, of class BlogHashtagDao.
     */
    @Test
    public void testGetBlogHashtagById() {
    }

    /**
     * Test of getAllBlogHashtags method, of class BlogHashtagDao.
     */
    @Test
    public void testGetAllBlogHashtags() {
    }

    /**
     * Test of removeBlogHashtag method, of class BlogHashtagDao.
     */
    @Test
    public void testRemoveBlogHashtag() {
    }

    /**
     * Test of removeBlogFromHashtag method, of class BlogHashtagDao.
     */
    @Test
    public void testRemoveBlogFromHashtag() {
    }

    /**
     * Test of removeHashtagFromBlog method, of class BlogHashtagDao.
     */
    @Test
    public void testRemoveHashtagFromBlog() {
    }

    @Test
    @Transactional
    public void testAddGetRemoveBlogHashtag() {
	
	// prerequisite: blog
	// get from test database
	Blog blg1 = blogDao.getBlogById(1);
	
	// prerequisite: hashtag
	// build to avoid conflicts
	Hashtag htg1 = new Hashtag();
	htg1.setName("#testhashtag1");
	
	Hashtag htg2 = new Hashtag();
	htg2.setName("#testhashtag2");
	
	hashtagDao.insertHashtag(htg1);
	hashtagDao.insertHashtag(htg2);
	
	// testing
	BlogHashtag blgHtg1 = new BlogHashtag();
	blgHtg1.setBlog(blg1);
	blgHtg1.setHashtag(htg1);
	
	BlogHashtag blgHtg2 = new BlogHashtag();
	blgHtg2.setBlog(blg1);
	blgHtg2.setHashtag(htg2);
	
	blgHtgDao.insertBlogHashtag(blgHtg1);
	blgHtgDao.insertBlogHashtag(blgHtg2);
	
	BlogHashtag result;
	
	result = blgHtgDao.getBlogHashtagById(blgHtg1.getId());
	
	assertNotNull(result);
	assertTrue(blg1.getId() == blgHtg1.getBlog().getId());
	assertTrue(htg1.getId() == blgHtg1.getHashtag().getId());
	
	result = blgHtgDao.getBlogHashtagById(blgHtg2.getId());
	
	assertNotNull(result);
	assertTrue(blg1.getId() == blgHtg2.getBlog().getId());
	assertTrue(htg2.getId() == blgHtg2.getHashtag().getId());
	
	blgHtgDao.removeBlogHashtag(blgHtg1);
	result = blgHtgDao.getBlogHashtagById(blgHtg1.getId());
	
	assertNull(result);
	
	blgHtgDao.removeBlogHashtag(blgHtg2);
	result = blgHtgDao.getBlogHashtagById(blgHtg2.getId());
	
	assertNull(result);
	
//	// cleanup
//	hashtagDao.removeHashtag(htg1);
//	hashtagDao.removeHashtag(htg2);
    }
}
