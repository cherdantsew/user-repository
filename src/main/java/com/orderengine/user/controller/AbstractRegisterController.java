package com.orderengine.user.controller;

import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.service.abstraction.IRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public abstract class AbstractRegisterController {

    private final IRegisterService registerService;

    @PostMapping("/register")
    protected void register(@RequestBody RegisterDataDto registerDataDto) {
        registerService.register(registerDataDto);
    }

}
