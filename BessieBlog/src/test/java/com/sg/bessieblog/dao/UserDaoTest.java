/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.User;

import java.util.List;
import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
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
public class UserDaoTest {

    @Inject
    private UserDao userDao;

    @Inject
    private RoleDao roleDao;

    public UserDaoTest() {
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

    @Test
    @Transactional
    public void testInsertGetUser() {

        User u1 = new User();
        u1.setName("Bessie2");
        u1.setEmail("b2@gmail.com");

        Role r1 = roleDao.getRoleById(1);

        u1.setRole(r1);

        userDao.insertUser(u1);
        int u1Id = u1.getId();

        assertEquals(u1.getRole().getName(), "Admin");

        User u1From = userDao.getUserById(u1Id);

        assertEquals(u1From.getId(), u1Id);
        assertEquals(u1From.getName(), u1.getName());
        assertEquals(u1From.getEmail(), u1.getEmail());
        assertEquals(u1From.getRole().getId(), u1.getRole().getId());

    }

    @Test
    @Transactional
    public void testGetAllUsersRemoveUser() {
        
        List<User> all = userDao.getAllUsers();
        
        //this assert confirms my getAllUsers method works
        assertEquals(all.size(), 5);
        
        User u1 = userDao.getUserById(2);        
        assertEquals(u1.getName(), "Gina");
        
        userDao.removeUser(u1);
        
        all = userDao.getAllUsers();
        
        //this assert confirms my removeUser method works
        assertEquals(all.size(), 4);
        
        User u2 = userDao.getUserById(4);        
        assertEquals(u2.getName(), "Ken");
        
        userDao.removeUser(u2);
        
        all = userDao.getAllUsers();
        
        //this assert confirms my removeUser method works
        assertEquals(all.size(), 3);
               
    }

    @Test
    @Transactional
    public void testGetAllAdmins() {
        
        //test database was preloaded with 1 admin
        
        List<User> all = userDao.getAllAdmins();
  
        assertEquals(all.size(), 1);
        
    }
   
    @Test
    @Transactional
    public void testGetAllContributors() {
        
        //test database was preloaded with 2 contributors
        
        List<User> all = userDao.getAllContributors();
       
        assertEquals(all.size(), 2);
        
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        
        User u1 = userDao.getUserById(2);        
        assertEquals(u1.getName(), "Gina");
        assertEquals(u1.getEmail(), "g@gmail.com");
        assertEquals(u1.getRole().getId(), 2);
        
        u1.setName("Gina2");
        u1.setEmail("g2@gmail.com");
        
        Role r1 = roleDao.getRoleById(1);
        u1.setRole(r1);
        
        userDao.updateUser(u1);
        
        assertEquals(u1.getName(), "Gina2");
        assertEquals(u1.getEmail(), "g2@gmail.com");
        assertEquals(u1.getRole().getId(), 1);
        
    }

}
