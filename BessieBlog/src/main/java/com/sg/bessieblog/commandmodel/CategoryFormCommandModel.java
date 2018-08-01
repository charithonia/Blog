/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.commandmodel;

import com.sg.bessieblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author main
 */
public class CategoryFormCommandModel {
    private int categoryId;
    private String name;
    private List<StaticPage> spList;

    public List<StaticPage> getSpList() {
        return spList;
    }

    public void setSpList(List<StaticPage> spList) {
        this.spList = spList;
    }

    
    
    public int getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
