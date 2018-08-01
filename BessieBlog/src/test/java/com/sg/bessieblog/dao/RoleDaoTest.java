/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Role;

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
public class RoleDaoTest {
    
    @Inject
    private RoleDao roleDao;
    
    public RoleDaoTest() {
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
     * Test of insertRole method, of class RoleDao.
     */
    @Test
    public void testInsertRole() {
    }

    /**
     * Test of getRoleById method, of class RoleDao.
     */
    @Test
    public void testGetRoleById() {
    }

    /**
     * Test of getAllRoles method, of class RoleDao.
     */
    @Test
    public void testGetAllRoles() {
    }

    /**
     * Test of removeRole method, of class RoleDao.
     */
    @Test
    public void testRemoveRole() {
    }

    @Test
    @Transactional
    public void testAddGetRemoveRole() {
	Role role1 = new Role();
	role1.setName("Test Role 1");
	
	Role role2 = new Role();
	role2.setName("Test Role 2");
	
    	roleDao.insertRole(role1);
    	roleDao.insertRole(role2);
	
	Role result;
	
	result = roleDao.getRoleById(role1.getId());
	
	assertNotNull(result);
	assertEquals("Test Role 1", role1.getName());
	
	result = roleDao.getRoleById(role2.getId());
	
	assertNotNull(result);
	assertEquals("Test Role 2", role2.getName());
	
	roleDao.removeRole(role1);
	result = roleDao.getRoleById(role1.getId());
	
	assertNull(result);
	
	roleDao.removeRole(role2);
	result = roleDao.getRoleById(role2.getId());
	
	assertNull(result);
    }
    
    @Test
    @Transactional
    public void testUpdateRole() {
	Role role = new Role();
	role.setName("Role Name");
	
	roleDao.insertRole(role);
	
	Role roleNew = new Role();
	roleNew.setId(role.getId());
	roleNew.setName("New Role Name");
	
	roleDao.updateRole(roleNew);
	
	Role result = roleDao.getRoleById(role.getId());
	
	assertEquals("New Role Name", result.getName());
    }
    
    @Test
    @Transactional
    public void testGetRoleByUserId() {
        Role r = roleDao.getRoleByUserId(1);
        assertEquals("Admin", r.getName());
        r = roleDao.getRoleByUserId(3);
        assertEquals("Contributor", r.getName());
    }
}
