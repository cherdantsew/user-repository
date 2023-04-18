package com.orderengine.user.controller.courier;

import com.orderengine.user.controller.AbstractAuthenticationController;
import com.orderengine.user.service.UserAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courier/user-service")
public class CourierAuthenticationController extends AbstractAuthenticationController {

    public CourierAuthenticationController(UserAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
