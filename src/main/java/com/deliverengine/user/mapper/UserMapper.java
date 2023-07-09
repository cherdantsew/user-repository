package com.deliverengine.user.mapper;

import com.deliverengine.user.model.dto.CourierDto;
import com.deliverengine.user.model.dto.ExtendedUserDto;
import com.deliverengine.user.model.entity.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<CourierDto> toDto(List<User> users);
    CourierDto toDto(User user);
    ExtendedUserDto toExtendedDto(User user);
}
