package com.deliverengine.user.model.dto;

import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {
    private String login;
    private RolesConstants role;

    public static UserDto fromUser(User user) {
        return new UserDto(user.getLogin(), user.getRoles().stream().findFirst().orElseThrow().getRoleName());
    }
}
