package com.deliverengine.user.controller;

import com.deliverengine.user.model.dto.UserAuthDataDto;
import com.deliverengine.user.service.abstraction.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractAuthenticationController {

    private final IAuthenticationService authenticationService;

    public AbstractAuthenticationController(IAuthenticationService iAuthenticationService) {
        this.authenticationService = iAuthenticationService;
    }

    @PostMapping("/authenticate")
    protected ResponseEntity<Object> authenticate(@RequestBody UserAuthDataDto userAuthDataDto) {
        return authenticationService.authenticate(userAuthDataDto);
    }
}
