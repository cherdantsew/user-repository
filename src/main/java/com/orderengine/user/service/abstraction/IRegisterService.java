package com.orderengine.user.service.abstraction;

import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;

public interface IRegisterService {
    UserDto register(RegisterDataDto registerDataDto);
}
