/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.RoleFormCommandModel;
import com.sg.bessieblog.dto.Role;
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

/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class RoleServiceTest {

    @Inject
    private RoleService roleService;

    public RoleServiceTest() {
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
     * Test of insertRole method, of class RoleService.
     */
    @Test
    @Transactional
    public void testInsertRole() {

        Role role = new Role();

        role.setName("test");
        roleService.insertRole(role);

        Role from = roleService.getRoleById(role.getId());

        assertEquals(from.getName(), "test");

    }

    /**
     * Test of getRoleById method, of class RoleService.
     */
    @Test
    @Transactional
    public void testGetRoleById() {

        Role role = roleService.getRoleById(1);

        assertEquals(role.getName(), "Admin");
    }

    /**
     * Test of getAllRoles method, of class RoleService.
     */
    @Test
    @Transactional
    public void testGetAllRoles() {

        List<Role> all = roleService.getAllRoles();

        assertEquals(all.size(), 3);
    }

    /**
     * Test of removeRole method, of class RoleService.
     */
    @Test
    @Transactional
    public void testRemoveRole() {

        Role role = new Role();

        role.setName("test");
        roleService.insertRole(role);

        List<Role> all = roleService.getAllRoles();

        assertEquals(all.size(), 4);

        roleService.removeRole(role);

        all = roleService.getAllRoles();

        assertEquals(all.size(), 3);

    }
    
    @Test
    @Transactional
    public void testGetRoleByUserId() {
        Role r = roleService.getRoleByUserId(1);
        assertEquals("Admin", r.getName());
        r = roleService.getRoleByUserId(3);
        assertEquals("Contributor", r.getName());
    }

    /**
     * Test of updateRole method, of class RoleService.
     */
//    @Test
//    @Transactional
//    public void testUpdateRole() {
//
//        RoleFormCommandModel rfcm = new RoleFormCommandModel();
//        rfcm.setRoleId(1);
//        rfcm.setName("Admin2");
//        (1);
//        
//        roleService.updateRole(rfcm);
//
//        Role r = roleService.getRoleById
//        
//        
//        assertEquals("Admin2", r.getName());
//        
//    }

}
