package com.orderengine.user.utils;

import com.orderengine.user.model.enumeration.RolesConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.Optional;

public class SecurityUtils {

    private SecurityUtils(){

    }

    public static RolesConstants currentRole() {
        var role = (String) getSecurityContextDetails().orElseThrow().get("role");
        return role == null ? null : RolesConstants.valueOf(role);
    }
    private static Optional<Map<String, Object>> getSecurityContextDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) return Optional.empty();
        return Optional.of((Map<String, Object>) authentication.getDetails());
    }


}
