/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.commandmodel;

import com.sg.bessieblog.dto.StaticPage;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author matt
 */
public class RoleFormCommandModel {

    @NotNull(message = "You must supply a valid Role Id.")
    private int roleId;
    
    @NotEmpty(message = "You must supply a Role Name.")
    @Length(max = 50, message = "Name must be no more than 50 characters in length.") 
    private String name;
    
    private List<StaticPage> spList;

    public List<StaticPage> getSpList() {
        return spList;
    }

    public void setSpList(List<StaticPage> spList) {
        this.spList = spList;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
