package com.orderengine.user.service;

import com.orderengine.user.exception.http.BadRequestException;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.abstraction.IRegisterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CourierRegisterService implements IRegisterService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Transactional
    @Override
    public void register(RegisterDataDto registerDataDto) {
        userService.findUserByLogin(registerDataDto.getLogin()).ifPresent(user -> {
            throw new BadRequestException(String.format("User with login %s already exists.", registerDataDto.getLogin()));
        });
        User user = new User();
        user.setLogin(registerDataDto.getLogin());
        user.setPassword(registerDataDto.getPassword());
        user.setRoles(Set.of(roleService.getRoleByRoleName(RolesConstants.ROLE_COURIER)));
        userService.save(user);
    }
}
