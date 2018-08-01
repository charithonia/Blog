/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.viewmodel;

import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import java.util.List;

/**
 *
 * @author matt
 */
public class StaticPageViewModel {

    private String id;
    private String title;
    private String creationDate;
    private String publicationDate;
    private String expirationDate;
    private String userName;
    private String userRole;
    private String navOrder;
    private String isApproved;
    private String body;
    private String slug;
    private String userAuthorId;
    
    private List<StaticPage> fullStaticPageList;
    private List<StaticPage> activeStaticPageList;
    private List<StaticPage> pendingStaticPageList;
    private List<StaticPage> expiredStaticPageList;

    public String getUserAuthorId() {
        return userAuthorId;
    }

    public void setUserAuthorId(String userAuthorId) {
        this.userAuthorId = userAuthorId;
    }

    
    
    public List<StaticPage> getPendingStaticPageList() {
        return pendingStaticPageList;
    }

    public void setPendingStaticPageList(List<StaticPage> pendingStaticPageList) {
        this.pendingStaticPageList = pendingStaticPageList;
    }

    public List<StaticPage> getExpiredStaticPageList() {
        return expiredStaticPageList;
    }

    public void setExpiredStaticPageList(List<StaticPage> expiredStaticPageList) {
        this.expiredStaticPageList = expiredStaticPageList;
    }

    
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getNavOrder() {
        return navOrder;
    }

    public void setNavOrder(String navOrder) {
        this.navOrder = navOrder;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<StaticPage> getFullStaticPageList() {
        return fullStaticPageList;
    }

    public void setFullStaticPageList(List<StaticPage> fullStaticPageList) {
        this.fullStaticPageList = fullStaticPageList;
    }

    public List<StaticPage> getActiveStaticPageList() {
        return activeStaticPageList;
    }

    public void setActiveStaticPageList(List<StaticPage> activeStaticPageList) {
        this.activeStaticPageList = activeStaticPageList;
    }

    

    
}
