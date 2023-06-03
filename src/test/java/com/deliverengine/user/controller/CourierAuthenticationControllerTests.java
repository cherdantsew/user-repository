package com.deliverengine.user.controller;

public class CourierAuthenticationControllerTests extends AbstractAuthenticationControllerTests {

    public CourierAuthenticationControllerTests() {
        super("/courier/user-service/register", "/courier/user-service/authenticate");
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return true;
    }
}
