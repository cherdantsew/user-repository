package com.deliverengine.user.controller.courier;

import com.deliverengine.core.enumeration.CourierStatus;
import com.deliverengine.user.model.dto.CourierDto;
import com.deliverengine.user.model.dto.UpdateCourierStatusRequestDto;
import com.deliverengine.user.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/courier/user-service")
public class InternalCourierController {

    private final UserService userService;

    public InternalCourierController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/couriers")
    public List<CourierDto> findAllCouriers() {
        return userService.findAll();
    }

    @GetMapping("/couriers/courier-status/{status}")
    public List<CourierDto> findAllByCourierStatus(@PathVariable CourierStatus status) {
        return userService.findAllByCourierStatusIs(status);
    }

    @GetMapping("/{id}")
    CourierDto findCourierById(@PathVariable Long id) {
        return userService.findCourierById(id);
    }

    @GetMapping
    CourierDto findOneByLogin(@RequestParam String login) {
        return userService.findCourierByLogin(login);
    }

    @PutMapping
    Boolean updateCourierStatus(@RequestBody UpdateCourierStatusRequestDto requestDto) {
        return userService.updateCourierStatus(requestDto);
    }
}
