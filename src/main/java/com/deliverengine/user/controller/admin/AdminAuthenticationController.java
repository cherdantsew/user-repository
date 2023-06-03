package com.deliverengine.user.controller.admin;

import com.deliverengine.user.controller.AbstractAuthenticationController;
import com.deliverengine.user.service.admin.AdminAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminAuthenticationController extends AbstractAuthenticationController {

    public AdminAuthenticationController(AdminAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
