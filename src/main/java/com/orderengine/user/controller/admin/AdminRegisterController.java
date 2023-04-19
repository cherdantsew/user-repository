package com.orderengine.user.controller.admin;


import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.admin.AdminRegisterService;
import com.orderengine.user.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminRegisterController extends AbstractRegisterController {

    public AdminRegisterController(AdminRegisterService registerService) {
        super(registerService);
    }

    @Override
    protected ResponseEntity<UserDto> register(RegisterDataDto registerDataDto) {
        if (RolesConstants.ROLE_ADMIN == SecurityUtils.currentRole()) {
            return super.register(registerDataDto);
        } else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
