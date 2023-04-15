package com.orderengine.user.service;

import com.orderengine.user.model.entity.Role;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByRoleName(RolesConstants roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
