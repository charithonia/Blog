/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.viewmodel;

import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author matt
 */
public class RolePageViewModel {
    private List<Role> roleList;
    private List<StaticPage> staticPageList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<StaticPage> getStaticPageList() {
        return staticPageList;
    }

    public void setStaticPageList(List<StaticPage> staticPageList) {
        this.staticPageList = staticPageList;
    }
    
    
}
