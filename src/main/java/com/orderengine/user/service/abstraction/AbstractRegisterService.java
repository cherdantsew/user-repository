package com.orderengine.user.service.abstraction;

import com.orderengine.user.exception.http.BadRequestException;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.service.RoleService;
import com.orderengine.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

public abstract class AbstractRegisterService implements IRegisterService, IRoleProvider {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    public AbstractRegisterService(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserDto register(RegisterDataDto registerDataDto) {
        userService.findUserByLogin(registerDataDto.getLogin()).ifPresent(user -> {
            throw new BadRequestException(String.format("User with login %s already exists.", registerDataDto.getLogin()));
        });
        User user = new User();
        user.setLogin(registerDataDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerDataDto.getPassword()));
        user.setRoles(Set.of(roleService.getRoleByRoleName(getRole())));
        return UserDto.fromUser(userService.save(user));
    }
}
