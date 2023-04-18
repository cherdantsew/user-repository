package com.orderengine.user.controller.user;

import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.service.UserRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/user-service")
public class UserRegisterController extends AbstractRegisterController {

    public UserRegisterController(UserRegisterService userRegisterService) {
        super(userRegisterService);
    }

}
