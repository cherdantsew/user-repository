package com.deliverengine.user.model.dto;

import com.deliverengine.core.enumeration.CourierStatus;
import com.deliverengine.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourierDto {
    private Long id;
    private String login;
    private CourierStatus courierStatus;
    private String contactInfo;

    public static CourierDto fromUser(User user) {
        return new CourierDto(user.getId(), user.getLogin(), user.getCourierStatus(), user.getContactInfo());
    }
}
