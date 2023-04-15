package com.orderengine.user.service.abstraction;

import com.orderengine.user.model.dto.RegisterDataDto;

public interface IRegisterService {
    void register(RegisterDataDto registerDataDto);
}
