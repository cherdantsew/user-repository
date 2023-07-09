package com.deliverengine.user.model.dto;

import com.deliverengine.core.enumeration.CourierStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourierStatusRequestDto {
    private Long id;
    private CourierStatus courierStatus;
}
