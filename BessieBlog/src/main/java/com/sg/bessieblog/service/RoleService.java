/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.RoleFormCommandModel;
import com.sg.bessieblog.dto.Role;
import com.sg.bessieblog.viewmodel.RolePageViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
public interface RoleService {
    public Role insertRole(Role role);
    public Role getRoleById(int roleId);
    public List<Role> getAllRoles();
    public void removeRole(Role role);
     public void removeRole(int roleId); 
    public void updateRole(RoleFormCommandModel rfcm);

    public RolePageViewModel getRolePageViewModel();
    public RoleFormCommandModel getRoleFormCommandModel(Role role);

    public Role getRoleByUserId(int id);
}
