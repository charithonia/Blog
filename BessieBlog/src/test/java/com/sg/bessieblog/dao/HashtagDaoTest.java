/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.bessieblog.dto.Hashtag;

/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class HashtagDaoTest {
    
    @Inject
    private HashtagDao hashtagDao;
    
    public HashtagDaoTest() {
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
     * Test of insertHashtag method, of class HashtagDao.
     */
    @Test
    public void testInsertHashtag() {
    }

    /**
     * Test of getHashtagById method, of class HashtagDao.
     */
    @Test
    public void testGetHashtagById() {
    }

    /**
     * Test of getAllHashtags method, of class HashtagDao.
     */
    @Test
    public void testGetAllHashtags() {
    }

    /**
     * Test of removeHashtag method, of class HashtagDao.
     */
    @Test
    public void testRemoveHashtag() {
    }

    /**
     * Test of updateHashtag method, of class HashtagDao.
     */
    @Test
    public void testUpdateHashtag() {
    }

    @Test
    @Transactional
    public void testAddGetRemoveHashtag() {
	Hashtag htg1 = new Hashtag();
	htg1.setName("#fakenews");
	
	Hashtag htg2 = new Hashtag();
	htg2.setName("#manufacturingconsent");
	
	hashtagDao.insertHashtag(htg1);
	hashtagDao.insertHashtag(htg2);
	
	Hashtag result;
	
	result = hashtagDao.getHashtagById(htg1.getId());
	
	assertNotNull(result);
	assertEquals("#fakenews", result.getName());
	
	result = hashtagDao.getHashtagById(htg2.getId());
	
	assertNotNull(result);
	assertEquals("#manufacturingconsent", result.getName());
	
	hashtagDao.removeHashtag(htg1);
	result = hashtagDao.getHashtagById(htg1.getId());
	
	assertNull(result);
	
	hashtagDao.removeHashtag(htg2);
	result = hashtagDao.getHashtagById(htg2.getId());
	
	assertNull(result);
    }
    
    @Test
    @Transactional
    public void testGetAllHashtagsOrder() {
	Hashtag h = new Hashtag();
	h.setName("#hashtag");
	
	Hashtag h1 = new Hashtag();
	h1.setName("#hashtag1");
	
	Hashtag h2 = new Hashtag();
	h2.setName("#hashtag2");
	
	hashtagDao.insertHashtag(h);
	hashtagDao.insertHashtag(h1);
	hashtagDao.insertHashtag(h2);
	
	List<Hashtag> resultList = hashtagDao.getAllHashtags();
	
	Hashtag result0, result1, result2, result3;
	
	result0 = resultList.get(0);
	result1 = resultList.get(1);
	result2 = resultList.get(2);
	result3 = resultList.get(3);
	
	assertTrue(result0.getName().length() >= result1.getName().length());
	assertTrue(result1.getName().length() >= result2.getName().length());
	assertTrue(result2.getName().length() >= result3.getName().length());
    }
    
    @Test
    public void printHashtags() {
	List<Hashtag> hashtags = hashtagDao.getAllHashtags();
	System.out.println("################");
	for (Hashtag hashtag : hashtags) {
	    System.out.println(hashtag.getName());
	}
	System.out.println("################");
    }
}
