/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.StaticPageFormCommandModel;
import com.sg.bessieblog.dao.StaticPageDao;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.AdminStaticPageMgmtViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import com.sg.bessieblog.viewmodel.StaticPageViewModel;
import com.sg.bessieblog.viewmodel.UserStaticPageMgmtViewModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class StaticPageServiceImpl implements StaticPageService {

    private StaticPageDao staticPageDao;
    private CategoryService categoryService;
    private UserService userService;
    private BlogService blogService;
    private RoleService roleService;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void setStaticPageDao(StaticPageDao staticPageDao) {
        this.staticPageDao = staticPageDao;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public StaticPage makeStaticPageFromCommandModel(StaticPageFormCommandModel spfcm) {
        StaticPage sp = new StaticPage();

        //not null fields
        sp.setBody(spfcm.getBody());
        sp.setCreationDate((spfcm.getCreationDate() == null) ? LocalDateTime.now() : spfcm.getCreationDate());
        sp.setSlug(spfcm.getSlug());
        sp.setTitle(spfcm.getTitle());

        User user = userService.getUserById(Integer.valueOf(spfcm.getUserId()));

        sp.setUser(user);

        //"null-able" fields
        if (spfcm.getNavOrder().isEmpty()) {
            sp.setNavOrder(0);
        } else {
            sp.setNavOrder(Integer.valueOf(spfcm.getNavOrder()));
        }

        try {
            sp.setId(spfcm.getStaticPageId()); //null if adding, populated if editing
        } catch (NullPointerException e) {
        }

        try {
            sp.setExpirationDate(spfcm.getExpirationDate());
        } catch (NullPointerException e) {
        }

        sp.setIsApproved(Boolean.valueOf(spfcm.getIsApproved()));

        try {
            sp.setPublicationDate(spfcm.getPublicationDate());
        } catch (NullPointerException e) {
        }

        return sp;
    }

    @Override
    public StaticPage insertStaticPage(StaticPageFormCommandModel spfcm) {
        StaticPage sp = new StaticPage();

        sp.setBody(spfcm.getBody());
        sp.setCreationDate(LocalDateTime.now());
        sp.setExpirationDate(spfcm.getExpirationDate());
        sp.setIsApproved(Boolean.valueOf(spfcm.getIsApproved()));

        sp.setNavOrder(spfcm.getNavOrder().isEmpty() ? 0 : Integer.valueOf(spfcm.getNavOrder()));

        sp.setPublicationDate(spfcm.getPublicationDate());
        sp.setSlug(spfcm.getSlug());
        sp.setTitle(spfcm.getTitle());
        User u = userService.getUserById(Integer.valueOf(spfcm.getUserId()));
        sp.setUser(u);

        return staticPageDao.insertStaticPage(sp);
    }

    @Override
    public StaticPage getStaticPageById(int staticPageId) {
        return staticPageDao.getStaticPageById(staticPageId);
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return staticPageDao.getAllStaticPages();
    }

    @Override
    public void removeStaticPage(StaticPage staticPage) {
        staticPageDao.removeStaticPage(staticPage);
    }

    @Override
    public void removeStaticPage(int spId) {
        StaticPage sp = new StaticPage();
        sp.setId(spId);
        staticPageDao.removeStaticPage(sp);
    }

    @Override
    public void updateStaticPage(StaticPageFormCommandModel spfcm) {
        //need to fix test

        StaticPage sp = new StaticPage();

        sp.setBody(spfcm.getBody());
        sp.setCreationDate(spfcm.getCreationDate());
        sp.setExpirationDate(spfcm.getExpirationDate());
        sp.setId(Integer.valueOf(spfcm.getId()));
        sp.setIsApproved(Boolean.valueOf(spfcm.getIsApproved()));
        sp.setNavOrder(Integer.valueOf(spfcm.getNavOrder()));
        sp.setPublicationDate(spfcm.getPublicationDate());
        sp.setSlug(spfcm.getSlug());
        sp.setTitle(spfcm.getTitle());
        User u = userService.getUserById(Integer.valueOf(spfcm.getUserId()));
        sp.setUser(u);

        staticPageDao.updateStaticPage(sp);
    }

    @Override
    public List<StaticPage> getStaticPagesByUserId(User user) {
        return staticPageDao.getStaticPagesByUserId(user.getId());
    }

    @Override
    public List<StaticPage> getAllActiveStaticPages() {
        return staticPageDao.getAllActiveStaticPages();
    }

    @Override
    public List<StaticPage> getAllPendingStaticPages() {
        return staticPageDao.getAllPendingStaticPages();
    }

    @Override
    public StaticPageViewModel getStaticPageViewModel(StaticPage staticPage) {
        StaticPageViewModel spvm = new StaticPageViewModel();

        spvm.setId(Integer.toString(staticPage.getId()));
        spvm.setTitle(staticPage.getTitle());
        spvm.setBody(staticPage.getBody());
        spvm.setCreationDate(staticPage.getCreationDate().format(dtf));
        try {
            spvm.setPublicationDate(staticPage.getPublicationDate().format(dtf));
        } catch (NullPointerException e) {
        }
        try {
            spvm.setExpirationDate(staticPage.getExpirationDate().format(dtf));
        } catch (NullPointerException e) {
        }
        spvm.setIsApproved(String.valueOf(staticPage.getIsApproved()));

        try {
            spvm.setNavOrder(Integer.toString(staticPage.getNavOrder()));
        } catch (NullPointerException e) {
        }

        spvm.setSlug(staticPage.getSlug());
        spvm.setUserAuthorId(String.valueOf(staticPage.getUser().getId()));

        int userId = staticPage.getUser().getId();
        User u = userService.getUserById(userId);

        spvm.setUserName(u.getName());

        Role r = roleService.getRoleByUserId(u.getId());
        spvm.setUserRole(r.getName());

        spvm.setFullStaticPageList(staticPageDao.getAllStaticPages());

        return spvm;
    }

    @Override
    public StaticPageFormCommandModel getStaticPageFormCommandModel(StaticPage staticPage) {
        StaticPageFormCommandModel spfcm = new StaticPageFormCommandModel();

        spfcm.setBody(staticPage.getBody());
        spfcm.setCreationDate(staticPage.getCreationDate().toString());
        spfcm.setExpirationDate(staticPage.getExpirationDate().toString());
        spfcm.setId(Integer.toString(staticPage.getId()));
        spfcm.setIsApproved(String.valueOf(staticPage.getIsApproved()));
        spfcm.setNavOrder(Integer.toString(staticPage.getNavOrder()));
        spfcm.setPublicationDate(staticPage.getPublicationDate().toString());
        spfcm.setSlug(staticPage.getSlug());
        spfcm.setTitle(staticPage.getTitle());
        spfcm.setUserId(staticPage.getUser().getName());
        spfcm.setUserRole(staticPage.getUser().getRole().getName());

        spfcm.setSpList(staticPageDao.getAllStaticPages());
        return spfcm;
    }

//    @Override
//    public HomeViewModel getHomeViewModel() {
//
//        HomeViewModel hvm = new HomeViewModel();
//
//        //List<Blog> blogs = blogService.getAllActiveBlogs();
//        List<Blog> blogs = blogService.getBlogsByStatus("");
//        List<StaticPage> spList = staticPageDao.getAllActiveStaticPages();
//        List<Category> catList = categoryService.getAllCategories();
//
//        hvm.setBlogs(blogs);
//        hvm.setCategoryList(catList);
//        hvm.setStaticPages(spList);
//
//        return hvm;
//
//    }
    @Override
    public StaticPage eagerlyGetStaticPageById(int staticPageId) {
        StaticPage sp = staticPageDao.getStaticPageById(staticPageId);
        int spUserId = sp.getUser().getId();
        User spUser = userService.getUserById(spUserId);
        Role spUserRole = roleService.getRoleByUserId(spUser.getId());
        spUser.setRole(spUserRole);

        sp.setUser(spUser);

        return sp;
    }

    @Override
    public UserStaticPageMgmtViewModel getUserStaticPageMgmtViewModel(User user) {
        UserStaticPageMgmtViewModel uspmvm = new UserStaticPageMgmtViewModel();
        uspmvm.setPendingStaticPagesByUser(staticPageDao.getAllPendingStaticPagesByUserId(user.getId()));
        uspmvm.setActiveStaticPagesByUser(staticPageDao.getAllActiveStaticPagesByUserId(user.getId()));
        uspmvm.setExpiredStaticPagesByUser(staticPageDao.getAllExpiredStaticPagesByUserId(user.getId()));
        return uspmvm;
    }

    @Override
    public AdminStaticPageMgmtViewModel getAdminStaticPageMgmtViewModel() {
        AdminStaticPageMgmtViewModel aspmvm = new AdminStaticPageMgmtViewModel();
        aspmvm.setPendingStaticPages(staticPageDao.getAllPendingStaticPages());
        aspmvm.setActiveStaticPages(staticPageDao.getAllActiveStaticPages());
        aspmvm.setExpiredStaticPages(staticPageDao.getAllExpiredStaticPages());
        return aspmvm;
    }

    @Override
    public List<StaticPage> getAllActiveStaticPagesByUserId(int id) {
        return staticPageDao.getAllActiveStaticPagesByUserId(id);
    }

    @Override
    public List<StaticPage> getAllPendingStaticPagesByUserId(int id) {
        return staticPageDao.getAllPendingStaticPagesByUserId(id);
    }

    @Override
    public StaticPageViewModel getStaticPageViewModel() {
        StaticPageViewModel spvm = new StaticPageViewModel();

        List<StaticPage> fullSpList = getAllStaticPages();
        spvm.setFullStaticPageList(fullSpList);

        List<StaticPage> activeSpList = getAllActiveStaticPages();
        spvm.setActiveStaticPageList(activeSpList);

        return spvm;

    }

    @Override
    public List<StaticPage> getAllExpiredStaticPages() {
        return staticPageDao.getAllExpiredStaticPages();
    }

    @Override
    public List<StaticPage> getAllExpiredStaticPagesByUserId(int id) {
        return staticPageDao.getAllExpiredStaticPagesByUserId(id);
    }

    //make list of view models from list of static pages
    @Override
    public List<StaticPageViewModel> getListOfViewModelsFromListOfStaticPages(List<StaticPage> spList) {
        List<StaticPageViewModel> spvmList = new ArrayList();

        for (StaticPage currentSp : spList) {
            spvmList.add(getStaticPageViewModel(currentSp));
        }

        return spvmList;
    }

    @Override
    public HomeViewModel getHomeViewModel(int spId) {
        HomeViewModel hvm = new HomeViewModel();
        hvm.setPageToDisplay(getStaticPageById(spId));
        hvm.setStaticPages(getAllActiveStaticPages());
        return hvm;
    }
}

//    @Override
//    public List<StaticPage> getStaticPagesByStatus(String status) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<StaticPage> getStaticPagesByDate(LocalDateTime date) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<StaticPage> getStaticPagesByCategory(Category category) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    @Override
//    public List<StaticPage> getStaticPagesByHashtag(Hashtag hashtag) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
