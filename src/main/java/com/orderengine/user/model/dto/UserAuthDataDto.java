package com.orderengine.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserAuthDataDto {
    private String login;
    private String password;
}
