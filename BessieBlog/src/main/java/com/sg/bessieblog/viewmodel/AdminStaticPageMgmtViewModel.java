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
public class AdminStaticPageMgmtViewModel {
    private List<StaticPage> activeStaticPages;
    private List<StaticPage> pendingStaticPages;
    private List<StaticPage> expiredStaticPages;

    public List<StaticPage> getExpiredStaticPages() {
        return expiredStaticPages;
    }

    public void setExpiredStaticPages(List<StaticPage> expiredStaticPages) {
        this.expiredStaticPages = expiredStaticPages;
    }

    public List<StaticPage> getActiveStaticPages() {
        return activeStaticPages;
    }

    public void setActiveStaticPages(List<StaticPage> activeStaticPages) {
        this.activeStaticPages = activeStaticPages;
    }
    
    
    
    

    public List<StaticPage> getPendingStaticPages() {
        return pendingStaticPages;
    }

    public void setPendingStaticPages(List<StaticPage> pendingStaticPages) {
        this.pendingStaticPages = pendingStaticPages;
    }
    
    
}
