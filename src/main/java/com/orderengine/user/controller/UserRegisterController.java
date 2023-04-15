package com.orderengine.user.controller;

import com.orderengine.user.service.UserRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRegisterController extends AbstractRegisterController {

    public UserRegisterController(UserRegisterService userRegisterService) {
        super(userRegisterService);
    }
}
