package com.deliverengine.user.service.abstraction;

import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.dto.UserDto;

public interface IRegisterService {
    UserDto register(RegisterDataDto registerDataDto);
}
