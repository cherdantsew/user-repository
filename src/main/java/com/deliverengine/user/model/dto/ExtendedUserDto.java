package com.deliverengine.user.model.dto;

import com.deliverengine.user.model.entity.User;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExtendedUserDto {
    private Long id;
    private BigDecimal accountBalance;
    private String contactInfo;

    public static ExtendedUserDto fromUser(User user) {
        return new ExtendedUserDto(user.getId(), user.getAccountBalance(), user.getContactInfo());
    }
}
