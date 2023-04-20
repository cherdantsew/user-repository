package com.orderengine.user.service.admin;

import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.RoleService;
import com.orderengine.user.service.UserService;
import com.orderengine.user.service.abstraction.AbstractRegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminRegisterService extends AbstractRegisterService {

    public AdminRegisterService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        super(userService, roleService, passwordEncoder);
    }

    @Override
    public RolesConstants getRole() {
        return RolesConstants.ROLE_ADMIN;
    }
}
