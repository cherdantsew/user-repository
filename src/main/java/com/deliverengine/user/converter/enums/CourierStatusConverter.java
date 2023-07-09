package com.deliverengine.user.converter.enums;

import com.deliverengine.core.enumeration.CourierStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class CourierStatusConverter implements AttributeConverter<CourierStatus, String> {
    @Override
    public String convertToDatabaseColumn(CourierStatus courierStatus) {
        return courierStatus == null ? null : courierStatus.name();
    }

    @Override
    public CourierStatus convertToEntityAttribute(String s) {
        return Optional.ofNullable(s).map(CourierStatus::valueOf).orElse(null);
    }
}
