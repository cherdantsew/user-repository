package com.deliverengine.user.controller.user;

import com.deliverengine.user.controller.AbstractRegisterController;
import com.deliverengine.user.service.user.UserRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/user-service")
public class UserRegisterController extends AbstractRegisterController {

    public UserRegisterController(UserRegisterService userRegisterService) {
        super(userRegisterService);
    }

}
