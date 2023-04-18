package com.orderengine.user.controller;

import com.orderengine.user.model.dto.UserAuthDataDto;
import com.orderengine.user.service.abstraction.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public abstract class AbstractAuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody UserAuthDataDto userAuthDataDto) {
        return authenticationService.authenticate(userAuthDataDto);
    }
}
