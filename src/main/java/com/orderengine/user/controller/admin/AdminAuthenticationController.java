package com.orderengine.user.controller.admin;

import com.orderengine.user.controller.AbstractAuthenticationController;
import com.orderengine.user.service.AdminAuthenticationService;
import com.orderengine.user.service.abstraction.IAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminAuthenticationController extends AbstractAuthenticationController {

    public AdminAuthenticationController(AdminAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
