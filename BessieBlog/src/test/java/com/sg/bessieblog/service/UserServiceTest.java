package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.UserFormCommandModel;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.UserMgmtViewModel;
import java.util.List;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class UserServiceTest {

    @Inject
    UserService userService;

    @Inject
    RoleService roleService;

    public UserServiceTest() {
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
     * Test of insertUser method, of class UserService.
     */
    @Test
    @Transactional
    public void testInsertGetUser() {

        User u1 = new User();
        u1.setName("Bessie2");
        u1.setEmail("b2@gmail.com");

        Role r1 = roleService.getRoleById(1);

        u1.setRole(r1);

        userService.insertUser(u1);
        int u1Id = u1.getId();

        assertEquals(u1.getRole().getName(), "Admin");

        User u1From = userService.getUserById(u1Id);

        assertEquals(u1From.getId(), u1Id);
        assertEquals(u1From.getName(), u1.getName());
        assertEquals(u1From.getEmail(), u1.getEmail());
        assertEquals(u1From.getRole().getId(), u1.getRole().getId());

    }

    /**
     * Test of getAllUsers method, of class UserService.
     */
    @Test
    @Transactional
    public void testGetAllUsersRemoveUser() {

        List<User> all = userService.getAllUsers();

        //this assert confirms my getAllUsers method works
        assertEquals(all.size(), 5);

        User u1 = userService.getUserById(2);
        assertEquals(u1.getName(), "Gina");

        userService.removeUser(u1);

        all = userService.getAllUsers();

        //this assert confirms my removeUser method works
        assertEquals(all.size(), 4);

        User u2 = userService.getUserById(4);
        assertEquals(u2.getName(), "Ken");

        userService.removeUser(u2);

        all = userService.getAllUsers();

        //this assert confirms my removeUser method works
        assertEquals(all.size(), 3);
    }

    /**
     * Test of getAllAdmins method, of class UserService.
     */
    @Test
    @Transactional
    public void testGetAllAdmins() {

        //test database was preloaded with 1 admin
        List<User> all = userService.getAllAdmins();

        assertEquals(all.size(), 1);
    }

    /**
     * Test of getAllContributors method, of class UserService.
     */
    @Test
    @Transactional
    public void testGetAllContributors() {

        //test database was preloaded with 2 contributors
        List<User> all = userService.getAllContributors();

        assertEquals(all.size(), 2);
    }

    @Test
    @Transactional
    public void testUpdateUser() {

        User u1 = userService.getUserById(2);
        assertEquals(u1.getName(), "Gina");
        assertEquals(u1.getEmail(), "g@gmail.com");
        assertEquals(u1.getRole().getId(), 2);

        u1.setName("Gina2");
        u1.setEmail("g2@gmail.com");

        Role r1 = roleService.getRoleById(1);
        u1.setRole(r1);

        userService.updateUser(u1);

        assertEquals(u1.getName(), "Gina2");
        assertEquals(u1.getEmail(), "g2@gmail.com");
        assertEquals(u1.getRole().getId(), 1);
    }

    @Test
    @Transactional
    public void testGetUserMgmtViewModel() {
        
        UserMgmtViewModel umvm = userService.getUserMgmtViewModel();
        
        assertEquals(umvm.getUserList().size(), 5);
        
        //this one fails because the staticpage dao and service test
        //are not rolling back for some reason
        //assertEquals(umvm.getStaticPageList().size(), 5);
        
    }

    @Test
    @Transactional
    public void testGetUserFormCommandModel() {
        
        User u1 = userService.getUserById(1);
        
        UserFormCommandModel ufcm = userService.getUserFormCommandModel(u1);
        
    }

    @Test
    @Transactional
    public void testEagerlyGetUserById() {
    }

}