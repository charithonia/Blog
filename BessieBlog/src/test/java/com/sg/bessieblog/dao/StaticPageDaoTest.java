/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
public class StaticPageDaoTest {

    @Inject
    private StaticPageDao staticPageDao;
    

    public StaticPageDaoTest() {
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
     * Test of insertStaticPage method, of class StaticPageDao.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private StaticPage createAndFillStaticPage() {

        Role r = new Role();
        r.setId(1);

        User u = new User();
        u.setId(1);
        
        StaticPage sp = new StaticPage();
        sp.setBody("I've got the world on a string");
        sp.setCreationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        sp.setExpirationDate(LocalDateTime.parse("2014-12-13T13:45:19"));
        sp.setIsApproved(Boolean.TRUE);
        sp.setNavOrder(1);
        sp.setPublicationDate(LocalDateTime.parse("2015-12-13T13:45:19"));
        sp.setSlug("My pet slug");
        sp.setTitle("Slug on a String");
        sp.setUser(u);

        return sp;
    }

     
    
    @Test
    @Transactional
    public void testInsertGetRemoveStaticPage() {
        //Arrange
        //create a new static page
        StaticPage newSp = createAndFillStaticPage();
        //Act
        newSp = staticPageDao.insertStaticPage(newSp);
        StaticPage fromDb = staticPageDao.getStaticPageById(newSp.getId());
        //Assert
        
        assertTrue(newSp.getId() == fromDb.getId());
        assertTrue(0 == newSp.getCreationDate().compareTo(fromDb.getCreationDate()));
        assertEquals(newSp.getBody(), fromDb.getBody());
        assertEquals(newSp.getSlug(), fromDb.getSlug());
        
        //Act
        staticPageDao.removeStaticPage(newSp);
        
        //Assert
        
        assertNull(staticPageDao.getStaticPageById(newSp.getId()));
        
    }

    /**
     * Test of getAllStaticPages method, of class StaticPageDao.
     */
    @Test
    @Transactional
    public void testGetAllStaticPages() {
//        StaticPage sp1 = createAndFillStaticPage();
//        StaticPage sp2 = createAndFillStaticPage();
//        
//        sp1 = staticPageDao.insertStaticPage(sp1);
//        sp2 = staticPageDao.insertStaticPage(sp2);
        
        List<StaticPage> pages = staticPageDao.getAllStaticPages();
        
        assertTrue(5==pages.size());
        
        
    }

    /**
     * Test of removeStaticPage method, of class StaticPageDao.
     */
    /**
     * Test of updateStaticPage method, of class StaticPageDao.
     */
    @Test
    @Transactional
    public void testUpdateStaticPage() {
        StaticPage originalSp = staticPageDao.getStaticPageById(1);
        originalSp.setBody("Slug time");
        staticPageDao.updateStaticPage(originalSp);
        StaticPage fromDb = staticPageDao.getStaticPageById(originalSp.getId());
        
        assertEquals("Slug time", fromDb.getBody());
        
    }

    /**
     * Test of getStaticPagesByStatus method, of class StaticPageDao.
     */
    

    /**
     * Test of getStaticPagesByDate method, of class StaticPageDao.
     */
    

    /**
     * Test of getStaticPagesByCategory method, of class StaticPageDao.
     */
    
    /**
     * Test of getStaticPagesByUserId method, of class StaticPageDao.
     */
    @Test
    public void testGetStaticPagesByUserId() {
        List<StaticPage> spList = staticPageDao.getStaticPagesByUserId(1);
        
        assertTrue(1 == spList.size());
        
        
    }

    /**
     * Test of getStaticPagesByHashtag method, of class StaticPageDao.
     */
    
    /**
     * Test of getAllActiveStaticPages method, of class StaticPageDao.
     */
    @Test
    public void testGetAllActiveStaticPages() {
        List<StaticPage> spList = staticPageDao.getAllActiveStaticPages();
        assertTrue(2 == spList.size());
    }

    /**
     * Test of getAllPendingStaticPages method, of class StaticPageDao.
     */
    @Test
    public void testGetAllPendingStaticPages() {
        List<StaticPage> spList = staticPageDao.getAllPendingStaticPages();
        assertTrue(2 == spList.size());
    }

    @Test
    public void testGetAllExpiredStaticPages() {
        List<StaticPage> spList = staticPageDao.getAllExpiredStaticPages();
        assertTrue(1 == spList.size());
    }
    
}
