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
public class UserMgmtViewModel {
    private List<User> userList;
    private List<StaticPage> staticPageList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<StaticPage> getStaticPageList() {
        return staticPageList;
    }

    public void setStaticPageList(List<StaticPage> staticPageList) {
        this.staticPageList = staticPageList;
    }
    
    
}
