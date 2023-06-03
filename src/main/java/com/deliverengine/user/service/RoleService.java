package com.deliverengine.user.service;

import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.model.entity.Role;
import com.deliverengine.user.repository.RoleRepository;
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
