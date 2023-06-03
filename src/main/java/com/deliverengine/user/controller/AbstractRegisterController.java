package com.deliverengine.user.controller;

import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.dto.UserDto;
import com.deliverengine.user.service.abstraction.IRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public abstract class AbstractRegisterController {

    private final IRegisterService registerService;

    @PostMapping("/register")
    protected ResponseEntity<UserDto> register(@RequestBody RegisterDataDto registerDataDto) {
        return new ResponseEntity(registerService.register(registerDataDto), HttpStatus.OK);
    }
}
