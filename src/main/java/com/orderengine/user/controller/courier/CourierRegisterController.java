package com.orderengine.user.controller.courier;


import com.orderengine.user.controller.AbstractRegisterController;
import com.orderengine.user.service.courier.CourierRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courier/user-service")
public class CourierRegisterController extends AbstractRegisterController {

    public CourierRegisterController(CourierRegisterService registerService) {
        super(registerService);
    }

}
