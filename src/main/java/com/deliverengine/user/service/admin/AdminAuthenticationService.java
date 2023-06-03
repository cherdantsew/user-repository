package com.deliverengine.user.service.admin;

import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.core.security.jwt.TokenProvider;
import com.deliverengine.user.service.AbstractCommonAuthenticationService;
import com.deliverengine.user.service.UserService;
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
