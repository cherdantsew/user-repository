package com.deliverengine.user.controller.courier;


import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.core.utils.SecurityUtils;
import com.deliverengine.user.controller.AbstractRegisterController;
import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.dto.UserDto;
import com.deliverengine.user.service.courier.CourierRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    protected ResponseEntity<UserDto> register(@RequestBody RegisterDataDto registerDataDto) {
        if (RolesConstants.ROLE_ADMIN == SecurityUtils.currentRole()) {
            return super.register(registerDataDto);
        } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
