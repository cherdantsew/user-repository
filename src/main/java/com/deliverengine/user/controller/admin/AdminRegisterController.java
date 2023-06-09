package com.deliverengine.user.controller.admin;


import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.core.utils.SecurityUtils;
import com.deliverengine.user.controller.AbstractRegisterController;
import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.dto.UserDto;
import com.deliverengine.user.service.admin.AdminRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminRegisterController extends AbstractRegisterController {

    public AdminRegisterController(AdminRegisterService registerService) {
        super(registerService);
    }

    @Override
    @PostMapping("/register")
    protected ResponseEntity<UserDto> register(@RequestBody RegisterDataDto registerDataDto) {
        if (RolesConstants.ROLE_ADMIN == SecurityUtils.currentRole()) {
            return super.register(registerDataDto);
        } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
