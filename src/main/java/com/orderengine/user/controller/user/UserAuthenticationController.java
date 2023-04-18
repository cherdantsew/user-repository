package com.orderengine.user.controller.user;

import com.orderengine.user.controller.AbstractAuthenticationController;
import com.orderengine.user.service.UserAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/user-service")
public class UserAuthenticationController extends AbstractAuthenticationController {

    public UserAuthenticationController(UserAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
