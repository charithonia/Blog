/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.commandmodel;

import java.time.LocalDateTime;

import java.util.List;

import com.sg.bessieblog.dto.Category;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matt
 */
public class BlogFormCommandModel {
    private int blogId;    
    private String title;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime experiationDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime publicationDate;
    private int userId;
    private int categoryId;
    private String body;
    private boolean isApproved;
    private List<Category> categoryList;

    public int getBlogId() {
	return blogId;
    }

    public void setBlogId(int blogId) {
	this.blogId = blogId;
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

    public void setCreationDate(LocalDateTime creationDate) {
	this.creationDate = creationDate;
    }

    public LocalDateTime getExperiationDate() {
	return experiationDate;
    }

    public void setExperiationDate(LocalDateTime experiationDate) {
	this.experiationDate = experiationDate;
    }

    public LocalDateTime getPublicationDate() {
	return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
	this.publicationDate = publicationDate;
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public int getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public boolean isIsApproved() {
	return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
	this.isApproved = isApproved;
    }

    public List<Category> getCategoryList() {
	return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
	this.categoryList = categoryList;
    }
}
