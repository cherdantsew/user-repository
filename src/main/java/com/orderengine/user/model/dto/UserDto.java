package com.orderengine.user.model.dto;

import com.orderengine.user.model.entity.User;
import com.orderengine.user.model.enumeration.RolesConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    private String login;
    private RolesConstants role;

    public static UserDto fromUser(User user) {
        return new UserDto(user.getLogin(), user.getRoles().stream().findFirst().orElseThrow().getRoleName());
    }
}
