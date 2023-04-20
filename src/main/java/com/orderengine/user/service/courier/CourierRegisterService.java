package com.orderengine.user.service.courier;

import com.orderengine.user.exception.http.BadRequestException;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.RoleService;
import com.orderengine.user.service.UserService;
import com.orderengine.user.service.abstraction.AbstractRegisterService;
import com.orderengine.user.service.abstraction.IRegisterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CourierRegisterService extends AbstractRegisterService {

    public CourierRegisterService(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        super(userService, roleService, passwordEncoder);
    }

    @Override
    public RolesConstants getRole() {
        return RolesConstants.ROLE_COURIER;
    }
}
