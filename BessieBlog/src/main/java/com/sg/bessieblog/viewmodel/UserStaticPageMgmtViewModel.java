/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.viewmodel;

import com.sg.bessieblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author matt
 */
public class UserStaticPageMgmtViewModel {
    private List<StaticPage> activeStaticPagesByUser;
    private List<StaticPage> pendingStaticPagesByUser;
    private List<StaticPage> expiredStaticPagesByUser;

    public List<StaticPage> getExpiredStaticPagesByUser() {
        return expiredStaticPagesByUser;
    }

    public void setExpiredStaticPagesByUser(List<StaticPage> expiredStaticPagesByUser) {
        this.expiredStaticPagesByUser = expiredStaticPagesByUser;
    }

    public List<StaticPage> getActiveStaticPagesByUser() {
        return activeStaticPagesByUser;
    }

    public void setActiveStaticPagesByUser(List<StaticPage> activeStaticPagesByUser) {
        this.activeStaticPagesByUser = activeStaticPagesByUser;
    }
    
    
    
    

    public List<StaticPage> getPendingStaticPagesByUser() {
        return pendingStaticPagesByUser;
    }

    public void setPendingStaticPagesByUser(List<StaticPage> pendingStaticPagesByUser) {
        this.pendingStaticPagesByUser = pendingStaticPagesByUser;
    }
    
    
    
}
