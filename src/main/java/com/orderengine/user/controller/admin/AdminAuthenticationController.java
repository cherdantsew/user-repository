package com.orderengine.user.controller.admin;

import com.orderengine.user.controller.AbstractAuthenticationController;
import com.orderengine.user.service.AbstractCommonAuthenticationService;
import com.orderengine.user.service.admin.AdminAuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminAuthenticationController extends AbstractAuthenticationController {

    public AdminAuthenticationController(AdminAuthenticationService authenticationService) {
        super(authenticationService);
    }
}
