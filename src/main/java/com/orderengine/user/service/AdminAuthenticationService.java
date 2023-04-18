package com.orderengine.user.service;

import com.orderengine.user.model.dto.UserAuthDataDto;
import com.orderengine.user.service.abstraction.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService implements IAuthenticationService {
    @Override
    public ResponseEntity<Object> authenticate(UserAuthDataDto userAuthDataDto) {
        return null;
    }
}
