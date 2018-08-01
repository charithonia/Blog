/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.StaticPageFormCommandModel;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.AdminStaticPageMgmtViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import com.sg.bessieblog.viewmodel.StaticPageViewModel;
import com.sg.bessieblog.viewmodel.UserStaticPageMgmtViewModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class StaticPageServiceTest {

    @Inject
    private StaticPageService staticPageService;

    public StaticPageServiceTest() {
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
     * Test of insertStaticPage method, of class StaticPageService.
     */
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private StaticPage createAndFillStaticPage() {

        Role r = new Role();
        r.setId(1);
        r.setName("Admin");

        User u = new User();
        u.setId(1);
        u.setName("FredFlin");
        u.setRole(r);

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
    public void testMakeStaticPageFromCommandModel() {
        StaticPageFormCommandModel cm = new StaticPageFormCommandModel();

        cm.setBody("Monk fruit");
        cm.setCreationDate("2013-12-13T13:45:19");
        cm.setExpirationDate("2014-12-13T13:45:19");
        cm.setIsApproved("true");
        cm.setNavOrder("3");
        cm.setPublicationDate("2015-12-13T13:45:19");
        cm.setSlug("Lug");
        cm.setStaticPageId(8);
        cm.setTitle("Slug butter");

        User u = new User();
        u.setId(3);

        cm.setUserId(String.valueOf(u.getId()));

        StaticPage sp = staticPageService.makeStaticPageFromCommandModel(cm);

        assertEquals(cm.getBody(), sp.getBody());
        assertEquals(cm.getTitle(), sp.getTitle());
        assertEquals(cm.getStaticPageId(), sp.getId());
        assertEquals(cm.getExpirationDate(), sp.getExpirationDate());

    }

    @Test
    @Transactional
    public void testInsertGetRemoveStaticPage() {
        /*
        Role r = new Role();
        r.setId(1);
        r.setName("Admin");

        User u = new User();
        u.setId(1);
        u.setName("FredFlin");
        u.setRole(r);
        
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

         */

//Arrange
        //create a new static page
        StaticPageFormCommandModel spfcm = new StaticPageFormCommandModel();
        spfcm.setUserId("1");
        spfcm.setBody("I've got the world on a string");
        spfcm.setCreationDate("2013-12-13T13:45:19");
        spfcm.setExpirationDate("2014-12-13T13:45:19");
        spfcm.setIsApproved("true");
        spfcm.setNavOrder("1");
        spfcm.setPublicationDate("2015-12-13T13:45:19");
        spfcm.setSlug("My pet slug");
        spfcm.setTitle("Slug on a String");
        //Act
        StaticPage newSp = staticPageService.insertStaticPage(spfcm);
        StaticPage fromDb = staticPageService.getStaticPageById(newSp.getId());
        //Assert

        assertTrue(newSp.getId() == fromDb.getId());
        //assertTrue(0 == newSp.getCreationDate().compareTo(fromDb.getCreationDate()));
        assertEquals(newSp.getBody(), fromDb.getBody());
        assertEquals(newSp.getSlug(), fromDb.getSlug());

        //Act
        staticPageService.removeStaticPage(newSp);

        //Assert
        assertNull(staticPageService.getStaticPageById(newSp.getId()));

    }

    /**
     * Test of getAllStaticPages method, of class StaticPageService.
     */
    @Test
    @Transactional
    public void testGetAllStaticPages() {
        List<StaticPage> pages = staticPageService.getAllStaticPages();

        assertTrue(5 == pages.size());

    }

    /**
     * Test of removeStaticPage method, of class StaticPageService.
     */
    /**
     * Test of updateStaticPage method, of class StaticPageService.
     */
    @Test
    @Transactional
    public void testUpdateStaticPage() {

        StaticPageFormCommandModel spfcm = new StaticPageFormCommandModel();
        spfcm.setUserId("1");
        spfcm.setBody("I've got the world on a string");
        spfcm.setCreationDate("2013-12-13T13:45:19");
        spfcm.setExpirationDate(("2014-12-13T13:45:19"));
        spfcm.setIsApproved("true");
        spfcm.setNavOrder("1");
        spfcm.setPublicationDate("2015-12-13T13:45:19");
        spfcm.setSlug("My pet slug");
        spfcm.setTitle("Slug on a String");
        
        
        StaticPage toDb = staticPageService.insertStaticPage(spfcm);
        spfcm.setBody("Slug time");
        spfcm.setId(String.valueOf(toDb.getId()));
        staticPageService.updateStaticPage(spfcm);
        StaticPage fromDb = staticPageService.getStaticPageById(toDb.getId());

        assertEquals("Slug time", fromDb.getBody());

    }

    /**
     * Test of getStaticPagesByStatus method, of class StaticPageService.
     */
    /**
     * Test of getStaticPagesByDate method, of class StaticPageService.
     */
    /**
     * Test of getStaticPagesByCategory method, of class StaticPageService.
     */
    /**
     * Test of getStaticPagesByUserId method, of class StaticPageService.
     */
    @Test
    public void testGetStaticPagesByUserId() {
        User u = new User();
        u.setId(1);
        List<StaticPage> spList = staticPageService.getStaticPagesByUserId(u);

        assertTrue(1 == spList.size());

    }

    /**
     * Test of getStaticPagesByHashtag method, of class StaticPageService.
     */
    /**
     * Test of getAllActiveStaticPages method, of class StaticPageService.
     */
    @Test
    public void testGetAllActiveStaticPages() {
        List<StaticPage> spList = staticPageService.getAllActiveStaticPages();
        assertTrue(2 == spList.size());
    }

    /**
     * Test of getAllPendingStaticPages method, of class StaticPageService.
     */
    @Test
    public void testGetAllPendingStaticPages() {
        List<StaticPage> spList = staticPageService.getAllPendingStaticPages();
        assertTrue(2 == spList.size());
    }

    @Test
    public void testGetAllExpiredStaticPages() {
        List<StaticPage> spList = staticPageService.getAllExpiredStaticPages();
        assertTrue(1 == spList.size());
    }

    /**
     * Test of getStaticPageViewModel method, of class StaticPageService.
     */
    @Test
    @Transactional
    public void testGetStaticPageViewModel() {
        StaticPage sp = createAndFillStaticPage();
        StaticPageViewModel spvm = staticPageService.getStaticPageViewModel(sp);

        assertEquals(sp.getBody(), spvm.getBody());
        assertEquals(sp.getSlug(), sp.getSlug());
        assertEquals(String.valueOf(sp.getIsApproved()), spvm.getIsApproved());
        assertEquals(sp.getCreationDate().format(dtf), spvm.getCreationDate());
    }

    /**
     * Test of getStaticPageFormCommandModel method, of class StaticPageService.
     */
    @Test
    public void testGetStaticPageFormCommandModel() {
        StaticPage sp = createAndFillStaticPage();

        StaticPageFormCommandModel spfcm = staticPageService.getStaticPageFormCommandModel(sp);
        assertEquals("Slug on a String", spfcm.getTitle());
        assertEquals("My pet slug", spfcm.getSlug());
        assertTrue("true" == spfcm.getIsApproved());

        /*
                sp.setBody("I've got the world on a string");
        sp.setCreationDate(LocalDateTime.parse("2013-12-13T13:45:19"));
        sp.setExpirationDate(LocalDateTime.parse("2014-12-13T13:45:19"));
        sp.setIsApproved(Boolean.TRUE);
        sp.setNavOrder(1);
        sp.setPublicationDate(LocalDateTime.parse("2015-12-13T13:45:19"));
        sp.setSlug("My pet slug");
        sp.setTitle("Slug on a String");
        sp.setUser(u);
         */
    }

    /**
     * Test of getHomeViewModel method, of class StaticPageService.
     */
//    @Test
//    @Ignore
//    public void testGetHomeViewModel() {
//        HomeViewModel hvm = staticPageService.getHomeViewModel();
//
//        assertTrue(1 == hvm.getBlogs().size());
//        assertTrue(5 == hvm.getCategoryList().size());
//        assertTrue(3 == hvm.getStaticPages().size());
//
//    }

    /**
     * Test of eagerlyGetStaticPageById method, of class StaticPageService.
     */
    @Test
    public void testEagerlyGetStaticPageById() {
        StaticPage sp = staticPageService.eagerlyGetStaticPageById(1);

        User u = sp.getUser();
        Role r = u.getRole();

        assertEquals("Admin", r.getName());
        assertEquals("Bessie", u.getName());
        assertEquals("Page1", sp.getTitle());

    }

    /**
     * Test of getUserStaticPageMgmtViewModel method, of class
     * StaticPageService.
     */
    @Test
    public void testGetUserStaticPageMgmtViewModel() {
        User u = new User();
        u.setId(3);
        UserStaticPageMgmtViewModel uspmvm = staticPageService.getUserStaticPageMgmtViewModel(u);

        List<StaticPage> pending = uspmvm.getPendingStaticPagesByUser();
        List<StaticPage> active = uspmvm.getActiveStaticPagesByUser();
        List<StaticPage> expired = uspmvm.getExpiredStaticPagesByUser();
        
        assertTrue(1 == active.size());
        assertTrue(1 == pending.size());
        assertTrue(0 == expired.size());

    }

    /**
     * Test of getAdminStaticPageMgmtViewModel method, of class
     * StaticPageService.
     */
    @Test
    public void testGetAdminStaticPageMgmtViewModel() {
        User u = new User();
        u.setId(1);
        AdminStaticPageMgmtViewModel aspmvm = staticPageService.getAdminStaticPageMgmtViewModel();

        List<StaticPage> pending = aspmvm.getPendingStaticPages();
        List<StaticPage> active = aspmvm.getActiveStaticPages();
        List<StaticPage> expired = aspmvm.getExpiredStaticPages();

        assertTrue(2 == active.size());
        assertTrue(2 == pending.size());
        assertTrue(1 == expired.size());
        
    }

}
