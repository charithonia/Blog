/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class CategoryServiceTest {
    
    @Inject
    private CategoryService service;
    
    public CategoryServiceTest() {
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
     * Test of insertCategory method, of class CategoryService.
     */
    @Test
    public void testInsertCategory() {
    }

    /**
     * Test of getCategoryById method, of class CategoryService.
     */
    @Test
    public void testGetCategoryById_int() {
    }

    /**
     * Test of getCategoryById method, of class CategoryService.
     */
    @Test
    public void testGetCategoryById_0args() {
    }

    /**
     * Test of removeCategory method, of class CategoryService.
     */
    @Test
    public void testRemoveCategory() {
    }

    /**
     * Test of updateCategory method, of class CategoryService.
     */
    @Test
    public void testUpdateCategory() {
    }

    /**
     * Test of getCategoryPageViewModel method, of class CategoryService.
     */
    @Test
    public void testGetCategoryPageViewModel() {
    }

    /**
     * Test of getCategoryFormCommandModel method, of class CategoryService.
     */
    @Test
    public void testGetCategoryFormCommandModel() {
    }
    
//    @Test
//    public void testGetAllCategories(){
//        List<Category> catList = service.getAllCategories();
//        assertEquals(catList.size(),5);
//        
//    }

    
}
