package com.deliverengine.user.service.abstraction;

import com.deliverengine.user.model.dto.UserAuthDataDto;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {
    ResponseEntity<Object> authenticate(UserAuthDataDto userAuthDataDto);
}
