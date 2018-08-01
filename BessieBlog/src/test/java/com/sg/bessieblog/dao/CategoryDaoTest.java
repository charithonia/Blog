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
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.bessieblog.dto.Category;


/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class CategoryDaoTest {
    
    @Inject
    private CategoryDao catDao;
    
    public CategoryDaoTest() {
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
     * Test of insertCategory method, of class CategoryDao.
     */
    @Test
    public void testInsertCategory() {
	
    }

    /**
     * Test of getCategoryById method, of class CategoryDao.
     */
    @Test
    public void testGetCategoryById() {
    }

    /**
     * Test of getAllCategories method, of class CategoryDao.
     */
    @Test
    public void testGetAllCategories() {
    }

    /**
     * Test of removeCategory method, of class CategoryDao.
     */
    @Test
    public void testRemoveCategory() {
    }

    @Test
    @Transactional
    public void testAddGetRemoveCategory() {
	Category cat1 = new Category();
	cat1.setName("Restaurants");
	
	Category cat2 = new Category();
	cat2.setName("Travel");
	
	catDao.insertCategory(cat1);
	catDao.insertCategory(cat2);
	
	Category result;
	
	result = catDao.getCategoryById(cat1.getId());
	
	assertNotNull(result);
	assertEquals("Restaurants", result.getName());
	
	result = catDao.getCategoryById(cat2.getId());
	
	assertNotNull(result);
	assertEquals("Travel", result.getName());
	
	catDao.removeCategory(cat1);
	result = catDao.getCategoryById(cat1.getId());
	
	assertNull(result);
	
	catDao.removeCategory(cat2);
	result = catDao.getCategoryById(cat2.getId());
	
	assertNull(result);
    }
    
    @Test
    @Transactional
    public void testUpdateCategory() {
	Category cat1 = new Category();
	cat1.setName("Restaurants");
	
	catDao.insertCategory(cat1);
	
	Category cat1_new = new Category();
	cat1_new.setId(cat1.getId());
	cat1_new.setName("Dining");
	
	catDao.updateCategory(cat1_new);
	
	Category result = catDao.getCategoryById(cat1.getId());
	
	assertEquals("Dining", result.getName());
    }
}
