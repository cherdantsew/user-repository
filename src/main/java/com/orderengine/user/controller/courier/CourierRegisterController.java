package com.orderengine.user.controller.courier;


import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.service.CourierRegisterService;
import com.orderengine.user.service.abstraction.IRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courier/user-service")
public class CourierRegisterController extends AbstractRegisterController {

    public CourierRegisterController(CourierRegisterService registerService) {
        super(registerService);
    }

}
