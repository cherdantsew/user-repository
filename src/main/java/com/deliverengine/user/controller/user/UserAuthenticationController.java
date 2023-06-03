package com.deliverengine.user.controller.user;

import com.deliverengine.user.controller.AbstractAuthenticationController;
import com.deliverengine.user.service.user.UserAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/user/user-service")
public class UserAuthenticationController extends AbstractAuthenticationController {

    public UserAuthenticationController(UserAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
