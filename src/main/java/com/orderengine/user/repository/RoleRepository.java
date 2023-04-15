package com.orderengine.user.repository;

import com.orderengine.user.model.entity.Role;
import com.orderengine.user.model.enumeration.RolesConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
    Role findByRoleName(RolesConstants roleName);
}
