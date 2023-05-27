package com.orderengine.user.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.GrantedAuthority;

public enum AuthoritiesConstants implements GrantedAuthority {
    CURRENT_USER,
    CREATE_DELIVERY_ORDER,
    VIEW_DELIVERY_ORDER,
    VIEW_ALL_DELIVERY_ORDERS,
    ASSIGN_PARCEL_DELIVERY_ORDER,
    CHANGE_DELIVERY_DESTINATION,
    CHANGE_DELIVERY_STATUS,
    CANCEL_DELIVERY_ORDER,
    VIEW_ALL_COURIERS;
    @Override
    public String getAuthority() {
        return name();
    }
}
