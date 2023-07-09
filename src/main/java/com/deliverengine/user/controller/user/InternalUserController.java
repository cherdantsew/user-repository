package com.deliverengine.user.controller.user;

import com.deliverengine.user.mapper.UserMapper;
import com.deliverengine.user.model.dto.ExtendedUserDto;
import com.deliverengine.user.model.dto.UpdateUserAccountRequestDto;
import com.deliverengine.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/user/user-service")
public class InternalUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public InternalUserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping("/{id}")
    ExtendedUserDto findOneById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PutMapping
    Boolean updateUserAccount(@RequestBody UpdateUserAccountRequestDto requestDto){
       return userService.updateUserAccount(requestDto.getId(), requestDto.getAccountValue());
    }

    @GetMapping
    ExtendedUserDto findUserByLogin(@RequestParam String login) {
        return userService.findUserByLogin(login).map(userMapper::toExtendedDto).orElse(null);
    }
}
