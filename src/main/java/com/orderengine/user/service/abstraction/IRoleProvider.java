package com.orderengine.user.service.abstraction;

import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.enumeration.RolesConstants;

public interface IRoleProvider {
    RolesConstants getRole();
}
