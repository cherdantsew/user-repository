package com.deliverengine.user.service.abstraction;

import com.deliverengine.core.exception.http.BadRequestException;
import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.dto.UserDto;
import com.deliverengine.user.model.entity.User;
import com.deliverengine.user.service.RoleService;
import com.deliverengine.user.service.UserService;
import jakarta.transaction.Transactional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AbstractRegisterService implements IRegisterService, IRoleProvider {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public AbstractRegisterService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
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
