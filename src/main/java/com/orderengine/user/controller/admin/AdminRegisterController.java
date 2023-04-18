package com.orderengine.user.controller.admin;


import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.AdminRegisterService;
import com.orderengine.user.utils.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminRegisterController extends AbstractRegisterController {

    public AdminRegisterController(AdminRegisterService registerService) {
        super(registerService);
    }

    @Override
    protected void register(RegisterDataDto registerDataDto) {
        if (RolesConstants.ROLE_ADMIN == SecurityUtils.currentRole())
            super.register(registerDataDto);
    }
}
