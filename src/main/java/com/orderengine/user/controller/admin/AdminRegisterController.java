package com.orderengine.user.controller.admin;


import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.admin.AdminRegisterService;
import com.orderengine.user.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user-service")
public class AdminRegisterController {

    @Autowired
    AdminRegisterService adminRegisterService;
//    public AdminRegisterController(AdminRegisterService registerService) {
//        super(registerService);
//    }

//    @Override
    @PostMapping("/register")
    protected ResponseEntity<UserDto> register(@RequestBody RegisterDataDto registerDataDto, HttpServletRequest request) {
        if (RolesConstants.ROLE_ADMIN == SecurityUtils.currentRole()) {
            return new ResponseEntity(adminRegisterService.register(registerDataDto), HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
