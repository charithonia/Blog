/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.StaticPageFormCommandModel;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.AdminStaticPageMgmtViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import com.sg.bessieblog.viewmodel.StaticPageViewModel;
import com.sg.bessieblog.viewmodel.UserStaticPageMgmtViewModel;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author matt
 */
public interface StaticPageService {
    public StaticPage insertStaticPage(StaticPageFormCommandModel spfcm);
    public StaticPage makeStaticPageFromCommandModel(StaticPageFormCommandModel cm);
    public StaticPage getStaticPageById(int staticPageId);
    public List<StaticPage> getAllStaticPages();
    public void removeStaticPage(StaticPage staticPage);
    public void removeStaticPage(int spId);
    public void updateStaticPage(StaticPageFormCommandModel spfcm);
    public List<StaticPage> getStaticPagesByUserId(User user);
    public List<StaticPage> getAllActiveStaticPages();
    public List<StaticPage> getAllPendingStaticPages();
    public List<StaticPage> getAllExpiredStaticPages();
    public StaticPageViewModel getStaticPageViewModel(StaticPage staticPage);
    public StaticPageViewModel getStaticPageViewModel();
    public StaticPageFormCommandModel getStaticPageFormCommandModel(StaticPage staticPage);
    public HomeViewModel getHomeViewModel(int spId);
    public StaticPage eagerlyGetStaticPageById(int staticPageId);
    public UserStaticPageMgmtViewModel getUserStaticPageMgmtViewModel(User user);
    public AdminStaticPageMgmtViewModel getAdminStaticPageMgmtViewModel();
    public List<StaticPage> getAllActiveStaticPagesByUserId(int id);
    public List<StaticPage> getAllPendingStaticPagesByUserId(int id);
    public List<StaticPage> getAllExpiredStaticPagesByUserId(int id);
    public List<StaticPageViewModel> getListOfViewModelsFromListOfStaticPages(List<StaticPage> spList);
//    public List<StaticPage> getStaticPagesByHashtag(Hashtag hashtag);
//    public List<StaticPage> getStaticPagesByStatus(String status);
//    public List<StaticPage> getStaticPagesByDate(LocalDateTime date);
//    public List<StaticPage> getStaticPagesByCategory(Category category);


}
