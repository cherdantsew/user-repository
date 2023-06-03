package com.deliverengine.user.service.user;

import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.service.RoleService;
import com.deliverengine.user.service.UserService;
import com.deliverengine.user.service.abstraction.AbstractRegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService extends AbstractRegisterService {

    public UserRegisterService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        super(userService, roleService, passwordEncoder);
    }

    @Override
    public RolesConstants getRole() {
        return RolesConstants.ROLE_USER;
    }
}
