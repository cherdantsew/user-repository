package com.orderengine.user.service.admin;

import com.orderengine.user.config.TokenProvider;
import com.orderengine.user.model.dto.UserAuthDataDto;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.service.AbstractCommonAuthenticationService;
import com.orderengine.user.service.UserService;
import com.orderengine.user.service.abstraction.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService extends AbstractCommonAuthenticationService {
    public AdminAuthenticationService(UserService userService, TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        super(userService, tokenProvider, authenticationManager);
    }

    @Override
    protected RolesConstants getRoleByLoginUrl() {
        return RolesConstants.ROLE_ADMIN;
    }
}
