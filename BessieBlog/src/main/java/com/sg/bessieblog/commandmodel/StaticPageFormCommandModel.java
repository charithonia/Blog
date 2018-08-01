/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.commandmodel;

import com.sg.bessieblog.dto.StaticPage;
import java.time.LocalDateTime;

import com.sg.bessieblog.dto.User;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author main
 */
public class StaticPageFormCommandModel {

    private int staticPageId;

    //@NotEmpty(message="Title cannot be empty");
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expirationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime publicationDate;
    private String id;
    private String userRole;
    //@NotNull
    private String userId;
    private String navOrder;
    //@NotEmpty(message="Body cannot be empty");
    private String body;
    //@NotEmpty(message="Page nickname cannot be empty");
    private String slug;
    private String isApproved;

    private List<StaticPage> spList;

    public List<StaticPage> getSpList() {
        return spList;
    }

    public void setSpList(List<StaticPage> spList) {
        this.spList = spList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getStaticPageId() {
        return staticPageId;
    }

    public void setStaticPageId(int staticPageId) {
        this.staticPageId = staticPageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = LocalDateTime.parse(creationDate);
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

//    public void setExpirationDate(LocalDateTime expirationDate) {
//        this.expirationDate = expirationDate;
//    }
    public void setExpirationDate(String expirationDate) {
        if (expirationDate.isEmpty()) {
            this.expirationDate = null;
        } else {
            this.expirationDate = LocalDateTime.parse(expirationDate);
        }
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

//    public void setPublicationDate(LocalDateTime publicationDate) {
//        this.publicationDate = publicationDate;
//    }
    public void setPublicationDate(String publicationDate) {
        if (publicationDate.isEmpty()) {
            this.publicationDate = null;
        } else {
            this.publicationDate = LocalDateTime.parse(publicationDate);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNavOrder() {
        return navOrder;
    }

    public void setNavOrder(String navOrder) {
        this.navOrder = navOrder;
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

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

}
