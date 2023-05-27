package com.orderengine.user.controller.courier;


import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.courier.CourierRegisterService;
import com.orderengine.user.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courier/user-service")
public class CourierRegisterController extends AbstractRegisterController {

    public CourierRegisterController(CourierRegisterService registerService) {
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
