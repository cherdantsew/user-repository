package com.deliverengine.user.repository;

import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
    Role findByRoleName(RolesConstants roleName);
}
