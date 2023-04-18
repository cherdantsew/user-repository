package com.orderengine.user.service.abstraction;

import com.orderengine.user.model.dto.UserAuthDataDto;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {
    ResponseEntity<Object> authenticate(UserAuthDataDto userAuthDataDto);
}
