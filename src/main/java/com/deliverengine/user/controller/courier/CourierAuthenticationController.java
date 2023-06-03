package com.deliverengine.user.controller.courier;

import com.deliverengine.user.controller.AbstractAuthenticationController;
import com.deliverengine.user.service.courier.CourierAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courier/user-service")
public class CourierAuthenticationController extends AbstractAuthenticationController {

    public CourierAuthenticationController(CourierAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
